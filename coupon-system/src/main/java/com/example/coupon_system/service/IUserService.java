package com.example.coupon_system.service;

import com.example.coupon_system.persistence.entity.User;

public interface IUserService {
    User getUser(int id);
    User createUser(User user);
}
