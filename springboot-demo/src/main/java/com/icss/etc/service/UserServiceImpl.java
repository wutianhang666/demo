package com.icss.etc.service;

import com.icss.etc.pojo.User;
import com.icss.etc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public List<User> userList() {
        return userMapper.userList();
    }
}