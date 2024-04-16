package com.icss.etc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.icss.etc.mapper.SysMenuMapper;
import com.icss.etc.pojo.SysMenu;
import com.icss.etc.service.SysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
}
