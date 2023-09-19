package com.icss.etc.service.impl;

import com.icss.etc.mapper.UserMapper;
import com.icss.etc.pojo.User;
import com.icss.etc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public List<User> userList() {
        return userMapper.userList();
    }
}
