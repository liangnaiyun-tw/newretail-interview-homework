package com.example.coupon_system.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
    private List<Coupon> coupons;
}
