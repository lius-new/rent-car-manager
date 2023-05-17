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
    public User deleteUserById(int id) {
        return userMapper.deleteUserById(id);
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
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User register(User user) {
        int i = userMapper.insertUser(user);
        return i > 0 ? user : null;
    }
}
