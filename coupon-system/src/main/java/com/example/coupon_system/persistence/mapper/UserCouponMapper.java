package com.example.coupon_system.persistence.mapper;

import com.example.coupon_system.persistence.entity.UserCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCouponMapper {
    int createUserCoupon(UserCoupon userCoupon);
    int deleteUserCoupon(int id);

    int updateUserCoupon(UserCoupon userCoupon);

    UserCoupon getUserCouponByUserIdAndCouponId(@Param("userId") int userId, @Param("couponId") int couponId);

}
