package com.lius.service;

import com.lius.entity.User;

import java.util.List;

public interface UserService {
    int insertUser(User user);

    int updateUser(User user);

    User deleteUserById(int id);

    List<User> selectAllUser();

    User selectUserByUserName(String username);

    List<User>  selectUserByUserNameLike(String username);

    User login(User user);

    User register(User user);

    boolean checkLogin(User user);

    int changeUserRole(User user);

}
