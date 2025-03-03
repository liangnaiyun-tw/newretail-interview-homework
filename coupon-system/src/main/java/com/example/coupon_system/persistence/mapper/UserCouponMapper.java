package com.example.coupon_system.persistence.mapper;

import com.example.coupon_system.persistence.entity.UserCoupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCouponMapper {
    int createUserCoupon(UserCoupon userCoupon);
    int deleteUserCoupon(int id);

}
