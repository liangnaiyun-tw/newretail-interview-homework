package com.example.coupon_system.utils.rabbitmq.dto;

import com.example.coupon_system.persistence.entity.UserCoupon;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserCouponMsg implements Serializable {
    private String routingKey;
    private UserCoupon userCoupon;
}
