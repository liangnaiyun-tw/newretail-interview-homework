package com.example.coupon_system.service.impl;

import com.example.coupon_system.dto.UserCouponDto;
import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.persistence.mapper.UserCouponMapper;
import com.example.coupon_system.persistence.mapper.UserMapper;
import com.example.coupon_system.service.ICouponService;
import com.example.coupon_system.service.IUserCouponService;
import com.example.coupon_system.utils.enums.UserCouponStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCouponService implements IUserCouponService {

    private final UserCouponMapper userCouponMapper;
    private final ICouponService couponService;
    @Override
    public UserCoupon createUserCoupon(UserCoupon userCoupon) {
        userCouponMapper.createUserCoupon(userCoupon);
        return userCoupon;
    }

    @Override
    public UserCoupon getUserCouponByUserIdAndCouponId(UserCouponDto userCouponDto) {
        return userCouponMapper.getUserCouponByUserIdAndCouponId(userCouponDto.getUserId(), userCouponDto.getCouponId());
    }

    @Override
    public UserCoupon redeemCoupon(UserCouponDto userCouponDto) {
        UserCoupon userCoupon = getUserCouponByUserIdAndCouponId(userCouponDto);
        if (couponService.isCouponExpired(userCoupon.getCoupon())) {
            userCoupon.setStatus(UserCouponStatus.EXPIRED.getCode());
        } else if (couponService.isCouponValid(userCoupon.getCoupon())) {
            userCoupon.setStatus(UserCouponStatus.USED.getCode());
            userCoupon.setUsedAt(new Date());
        }

        userCouponMapper.updateUserCoupon(userCoupon);
        return userCoupon;
    }


}
