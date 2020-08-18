package com.lsy.service_acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.lsy.common.utils.Result;
import com.lsy.service_acl.service.AclIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname IndexController
 * @Description 权限首页控制器
 * @Date 2020/08/17 19:46
 */
@RestController
@RequestMapping("/admin/acl/index")
@Api(tags = "权限首页控制器")
public class AclIndexController {

    @Autowired
    private AclIndexService indexService;

    @ApiOperation("根据token获取用户信息")
    @GetMapping("info")
    public Result info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.success(userInfo);
    }

    @ApiOperation("获取菜单")
    @GetMapping("menu")
    public Result getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        Map<String,Object> map = new HashMap<>();
        map.put("permissionList",permissionList);
        return Result.success(map);
    }

    @ApiOperation("退出登录")
    @PostMapping("logout")
    public Result logout(){
        return Result.success();
    }

}

