package com.icss.etc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.icss.etc.mapper.SysUserMapper;
import com.icss.etc.pojo.SysUser;
import com.icss.etc.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
