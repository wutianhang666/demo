package com.icss.etc.service;

import com.icss.etc.pojo.User;

import java.util.List;

public interface UserService {
    
    public int addUser(User user);
    
    public List<User> userList();
}
