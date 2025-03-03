package com.example.coupon_system.persistence.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Coupon {
    private int id;
    private String name;
    private int type;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
}
