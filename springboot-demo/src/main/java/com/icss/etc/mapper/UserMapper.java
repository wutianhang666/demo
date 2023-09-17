package com.icss.etc.mapper;

import com.icss.etc.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    
    int addUser(User user);
    
    List<User> userList();
}
