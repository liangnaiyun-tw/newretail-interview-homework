package com.example.coupon_system.persistence.mapper;

import com.example.coupon_system.persistence.entity.CouponLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponLogMapper {
    int createLog(CouponLog couponLog);
    List<CouponLog> getLogs();
}
