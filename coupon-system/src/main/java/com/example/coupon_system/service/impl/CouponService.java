package com.example.coupon_system.service.impl;

import com.example.coupon_system.dto.UserCouponDto;
import com.example.coupon_system.persistence.entity.Coupon;
import com.example.coupon_system.persistence.entity.CouponLog;
import com.example.coupon_system.persistence.entity.User;
import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.persistence.mapper.CouponMapper;
import com.example.coupon_system.service.ICouponService;
import com.example.coupon_system.service.IUserService;
import com.example.coupon_system.utils.enums.UserCouponStatus;
import com.example.coupon_system.utils.rabbitmq.producer.WriteCouponProducer;
import com.example.coupon_system.utils.redis.RedisLockUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CouponService implements ICouponService {

    private final CouponMapper couponMapper;
    private final RedisTemplate redisTemplate;
    private final IUserService userService;
    private final RedisLockUtils redisLockUtils;
    private final WriteCouponProducer writeCouponProducer;
    @Override
    public void initializeCoupon() {
        List<Coupon> coupons = couponMapper.getCoupons();
        Date now = new Date();

        for(Coupon coupon : coupons) {
            if (isCouponValid(coupon)) {
                String name = coupon.getId() + "-" + coupon.getName();
                Instant endDate = coupon.getEndDate().toInstant();
                long expireTime = endDate.getEpochSecond() - Instant.now().getEpochSecond();

                redisTemplate.opsForValue().set(name, coupon.getQuantity(), expireTime, TimeUnit.SECONDS);

            }

        }
    }

    @Override
    public String getCoupon(UserCouponDto userCouponDto) {
        User currentUser = userService.getUser(userCouponDto.getUserId());
        if (currentUser == null) {
            return "User not found";
        }

        Coupon currentCoupon = getCouponById(userCouponDto.getCouponId());
        if (currentCoupon == null) {
            return "Coupon not found";
        }

        String key = "user:" + currentUser.getId() + ";coupon:" + currentCoupon.getId();
        String couponId = currentCoupon.getId() + "-" + currentCoupon.getName();
        String couponIdKey = "coupon:" + couponId;
        String requestId = couponId + Thread.currentThread().getId();

        if (!redisTemplate.hasKey(couponId)){
            return "Invalid coupon";
        }

        if (redisTemplate.hasKey(key)){
            return "Coupon already obtained";
        }


        boolean locked = redisLockUtils.lock(couponIdKey, requestId, 10);

        if (!locked){
            return "Coupon is being obtained by another user";
        }

        try {
            Object objNum = redisTemplate.opsForValue().get(couponId);
            int remaining = Integer.parseInt((String.valueOf(objNum)));
            if (remaining > 0){
                remaining--;
                redisTemplate.opsForValue().set(couponId, String.valueOf(remaining));
                UserCoupon newUserCoupon = new UserCoupon.UserCouponBuilder()
                        .userId(currentUser.getId())
                        .couponId(currentCoupon.getId())
                        .claimedAt(new Date())
                        .status(UserCouponStatus.UNUSED.getCode())
                        .build();

                redisTemplate.opsForValue().set(key, newUserCoupon);
                // rabbitmq
                writeCouponProducer.sendWriteCouponMessage(newUserCoupon);
            } else {
                return "Coupon out of stock";
            }
        } finally {
            redisLockUtils.unlock(couponIdKey, requestId);
        }

        return "";


    }

    @Override
    public Coupon getCouponById(int id) {
        return couponMapper.getCoupon(id);
    }

    @Override
    public void resetCoupon() {
        List<Coupon> coupons = couponMapper.getCoupons();
        for (Coupon coupon : coupons) {
            String name = coupon.getId() + "-" + coupon.getName();
            if (redisTemplate.hasKey(name)){
                redisTemplate.opsForValue().getOperations().delete(name);
            }
        }
        Set<String> keys = redisTemplate.keys("user:*");
        for (String key : keys) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }

    public boolean isCouponValid(Coupon coupon) {
        Instant now = Instant.now();

        Instant startInstant = coupon.getStartDate().toInstant();
        Instant endInstant = coupon.getEndDate().toInstant();
        return !now.isBefore(startInstant) && !now.isAfter(endInstant);
    }




}
