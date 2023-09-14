package com.icss.etc.mapper;

import com.icss.etc.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    
    int addUser(User user);
}
