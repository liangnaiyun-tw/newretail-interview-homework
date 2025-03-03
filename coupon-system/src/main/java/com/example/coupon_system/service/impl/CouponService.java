package com.example.coupon_system.service.impl;

import com.example.coupon_system.persistence.entity.Coupon;
import com.example.coupon_system.persistence.entity.CouponLog;
import com.example.coupon_system.persistence.mapper.CouponMapper;
import com.example.coupon_system.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CouponService implements ICouponService {

    private final CouponMapper couponMapper;
    private final RedisTemplate redisTemplate;
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

    public boolean isCouponValid(Coupon coupon) {
        Instant now = Instant.now();

        Instant startInstant = coupon.getStartDate().toInstant();
        Instant endInstant = coupon.getEndDate().toInstant();
        return !now.isBefore(startInstant) && !now.isAfter(endInstant);
    }
}
