package com.lsy.service_acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.MD5Util;
import com.lsy.common.utils.Result;
import com.lsy.service_acl.entity.AclUser;
import com.lsy.service_acl.service.AclRoleService;
import com.lsy.service_acl.service.AclUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/admin/acl/user")
@Api(tags = "用户控制器")
public class AclUserController {
    @Autowired
    private AclUserService userService;

    @Autowired
    private AclRoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page, @PathVariable Long limit, AclUser userQueryVo) {
        Page<AclUser> pageParam = new Page<>(page, limit);
        QueryWrapper<AclUser> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username",userQueryVo.getUsername());
        }

        IPage<AclUser> pageModel = userService.page(pageParam, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("items",pageModel.getRecords());
        map.put("total",pageModel.getTotal());
        return Result.success(map);
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public Result save(@RequestBody AclUser user) {
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        userService.save(user);
        return Result.success();
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public Result updateById(@RequestBody AclUser user) {
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        userService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Result.success();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Result.success(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return Result.success();
    }

    @ApiOperation(value = "根据ID获取用户名和昵称")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        AclUser user = userService.getById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("item",user);
        return Result.success(map);
    }
}

