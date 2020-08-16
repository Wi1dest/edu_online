package com.lsy.service_edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.entity.EduTeacher;
import com.lsy.service_edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname TeacherController
 * @Description 前端讲师控制器
 * @Date 2020/08/12 21:48
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = "前端讲师模块")
public class TeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("getTeacherPage/{page}/{limit}")
    @ApiOperation("前端讲师分页")
    public Result getTeacherPage(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);
        Map<String, Object> teacherPage = eduTeacherService.getTeacherPage(eduTeacherPage);
        return Result.success(teacherPage);
    }

    @GetMapping("getTeacherInfo/{id}")
    @ApiOperation("前端讲师详情页")
    public Result getTeacherInfo(@PathVariable String id){
        Map<String, Object> teacherInfo = eduTeacherService.getTeacherInfo(id);
        return Result.success(teacherInfo);
    }
}
