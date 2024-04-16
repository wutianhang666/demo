package com.icss.etc.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.SysMenu;
import com.icss.etc.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sysMenu")
@Api(value = "菜单管理", description = "菜单管理")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    //根据id查找菜单
    @GetMapping("/{id}")
    @ApiOperation("根据id查询")
    public Object getById(@PathVariable Integer id) {

        return BaseResult.success(sysMenuService.selectById(id));
    }

    //增加菜单
    @PostMapping
    @ApiOperation("新增菜单")
    public Object add(@RequestBody SysMenu sysMenu) {
        boolean insert = sysMenuService.insert(sysMenu);
        return BaseResult.success(insert);
    }

    //根据id删除菜单
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除")
    public Object deleteById(@PathVariable Integer id) {
        boolean b = sysMenuService.deleteById(id);
        return BaseResult.success(b);
    }

    //批量删除菜单
    @PostMapping("/del/batch")
    @ApiOperation("批量删除")
    public Object deleteBatch(@RequestBody List<Integer> ids) {
        boolean b = sysMenuService.deleteBatchIds(ids);
        return BaseResult.success(b);
    }

    //分页查找
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Object findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.like("name", name);
        wrapper.orderBy("sort");
        Page<SysMenu> sysMenuPage = sysMenuService.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return BaseResult.success(sysMenuPage);
    }

    @GetMapping("/getAllMenu")
    @ApiOperation("查询全部菜单")
    public Object getAllMenu(String name) {
        EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
        if (StrUtil.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        List<SysMenu> list = sysMenuService.selectList(wrapper);
        // 找出pid为null的一级菜单
        List<SysMenu> parentNodes = list.stream().filter(menu -> menu.getParentId() == null).collect(Collectors.toList());
        //找出一级菜单为null的二级菜单放到Children中
        for (SysMenu menu : parentNodes) {
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getParentId())).collect(Collectors.toList()));
        }
        return BaseResult.success(parentNodes);
    }

}
