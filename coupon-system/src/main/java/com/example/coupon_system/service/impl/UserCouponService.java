package com.example.coupon_system.service.impl;

import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.persistence.mapper.UserCouponMapper;
import com.example.coupon_system.service.IUserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCouponService implements IUserCouponService {

    private final UserCouponMapper userCouponMapper;
    @Override
    public UserCoupon createUserCoupon(UserCoupon userCoupon) {
        userCouponMapper.createUserCoupon(userCoupon);
        return userCoupon;
    }
}
