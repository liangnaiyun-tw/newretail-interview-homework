package com.example.coupon_system.persistence.mapper;

import com.example.coupon_system.persistence.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int createUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    User getUser(int id);
    List<User> getUsers();
}
