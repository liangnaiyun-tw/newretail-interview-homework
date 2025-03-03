package com.example.coupon_system.service;

import com.example.coupon_system.dto.UserCouponDto;
import com.example.coupon_system.persistence.entity.Coupon;
import com.example.coupon_system.persistence.entity.UserCoupon;

public interface ICouponService {
    void initializeCoupon();
    String getCoupon(UserCouponDto userCouponDto);
    Coupon getCouponById(int id);
    void resetCoupon();
    boolean isCouponValid(Coupon coupon);
    boolean isCouponExpired(Coupon coupon);
}
