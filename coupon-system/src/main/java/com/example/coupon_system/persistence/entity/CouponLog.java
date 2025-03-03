package com.example.coupon_system.persistence.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CouponLog {
    private int id;
    private int couponId;
    private int userId;
    private int operationType;
    private String description;
    private Date createdAt;
}
