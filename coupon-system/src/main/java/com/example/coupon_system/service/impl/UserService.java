package com.example.coupon_system.service.impl;

import com.example.coupon_system.persistence.entity.User;
import com.example.coupon_system.persistence.mapper.UserMapper;
import com.example.coupon_system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {

    private final UserMapper userMapper;
    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    @Override
    public User createUser(User user) {
        userMapper.createUser(user);
        return user;
    }

    @Override
    public List<User> getUserCoupons(int id, Integer status) {
        return userMapper.getUserCoupons(id, status);
    }
}
