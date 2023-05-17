package com.lius.service;

import com.lius.entity.User;

import java.util.List;

public interface UserService {
    int insertUser(User user);

    int updateUser(User user);

    User deleteUserById(int id);

    List<User> selectAllUser();

    User selectUserByUserName(String username);

}
