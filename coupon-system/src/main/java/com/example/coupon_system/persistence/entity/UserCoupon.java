package com.example.coupon_system.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserCoupon implements Serializable {
    private int id;
    private int userId;
    private int couponId;
    private int status;
    private Date usedAt;
    private Date claimedAt;
}
