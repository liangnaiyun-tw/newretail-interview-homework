package com.example.coupon_system.service;

import com.example.coupon_system.dto.UserCouponDto;
import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.persistence.mapper.UserMapper;

public interface IUserCouponService {
    UserCoupon createUserCoupon(UserCoupon userCoupon);

    UserCoupon getUserCouponByUserIdAndCouponId(UserCouponDto userCouponDto);

    UserCoupon redeemCoupon(UserCouponDto userCouponDto);
}
