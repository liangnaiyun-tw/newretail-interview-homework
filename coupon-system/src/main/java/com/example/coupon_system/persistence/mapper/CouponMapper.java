package com.example.coupon_system.persistence.mapper;

import com.example.coupon_system.persistence.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {
    int createCoupon(Coupon coupon);
    int updateCoupon(Coupon coupon);
    int deleteCoupon(int id);
    Coupon getCoupon(int id);
    List<Coupon> getCoupons();


}
