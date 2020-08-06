package com.lsy.service_edu.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_edu.dto.CourseDTO;
import com.lsy.service_edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduCourseController
 * @Description 课程管理控制器
 * @Date 2020/08/05 17:48
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api(tags = "课程管理")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("saveCourse")
    @ApiOperation("添加课程")
    public Result addCourse(@RequestBody CourseDTO courseDTO){
        String courseId = eduCourseService.saveCourse(courseDTO);
        return Result.success(courseId);
    }

    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation("根据课程ID获取课程信息")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseDTO courseInfo = eduCourseService.getCourseInfo(courseId);
        return Result.success(courseInfo);
    }

    @PutMapping("updateCourseInfo")
    @ApiOperation("更新课程信息")
    public Result updateCourseInfo(@RequestBody CourseDTO courseDTO){
        eduCourseService.updateCourseInfo(courseDTO);
        return Result.success();
    }

}
