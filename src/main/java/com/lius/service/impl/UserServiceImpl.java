package com.lius.service.impl;

import com.lius.dao.UserMapper;
import com.lius.entity.User;
import com.lius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserByUserName(String userName) {
        return userMapper.deleteUserByUserName(userName);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public User selectIdByUserName(String username) {
        return userMapper.selectIdByUserName(username);
    }

    @Override
    public List<User> selectUserByUserNameLike(String username) {
        return userMapper.selectUserByUserNameLike(username);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User register(User user) {
        int i = userMapper.insertUser(user);
        // 注册成功，返回数据库中的数据，否则返回null
        return i > 0 ? userMapper.selectUserByUserName(user.getUserName()) : null;
    }

    @Override
    public boolean checkLogin(User user) {
        List<User> users = userMapper.checkUser(user);
        return users.size() == 1;
    }

    @Override
    public int changeUserRole(User user) {
        return userMapper.changeUserRole(user);
    }
}
