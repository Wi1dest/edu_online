package com.lsy.service_edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.entity.EduTeacher;
import com.lsy.service_edu.service.EduCourseService;
import com.lsy.service_edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname IndexController
 * @Description 首页
 * @Date 2020/08/10 21:59
 */
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
@Api(tags = "网站首页")
public class IndexController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    @ApiOperation("首页")
    public Result index() {
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.orderByDesc("id").last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapperCourse);
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id").last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        Map<String,Object> map = new HashMap<>();
        map.put("courseList",courseList);
        map.put("teacherList",teacherList);
        return Result.success(map);
    }
}
