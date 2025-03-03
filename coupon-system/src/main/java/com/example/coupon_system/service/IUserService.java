package com.example.coupon_system.service;

import com.example.coupon_system.persistence.entity.User;

import java.util.List;

public interface IUserService {
    User getUser(int id);
    User createUser(User user);

    List<User> getUserCoupons(int id, Integer status);
}
