package com.example.coupon_system.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class Coupon implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private int type;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
}
