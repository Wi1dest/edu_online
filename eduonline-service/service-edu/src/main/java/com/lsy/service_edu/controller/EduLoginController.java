package com.lsy.service_edu.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduLoginController
 * @Description 讲师登录控制器
 * @Date 2020/08/03 17:50
 */
@RestController
@RequestMapping("/eduservice/user")
@Api(tags = "讲师登录控制器")
@CrossOrigin
public class EduLoginController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("讲师登录")
    @PostMapping("login")
    public Result login(){
        Map<String,String> token = new HashMap<>();
        token.put("token","admin");
        return Result.success(token);
    }

    @ApiOperation("讲师资料")
    @GetMapping("info")
    public Result info(){
        Map<String,String> info = new HashMap<>();
        info.put("roles","[admin]");
        info.put("name","admin");
        info.put("avatar","https://gitee.com/static/images/logo-black.svg?t=158106664");
        return Result.success(info);
    }
}
