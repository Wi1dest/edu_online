package com.lsy.service_edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.dto.CourseDTO;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.service.EduCourseService;
import com.lsy.service_edu.vo.CourseFrontVO;
import com.lsy.service_edu.vo.CourseVO;
import com.lsy.service_edu.vo.course.CoursePublishVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("getCourseInfoOnService/{courseId}")
    @ApiOperation("根据课程ID获取课程信息[内部调用]")
    public CourseFrontVO getCourseInfoOnService(@PathVariable String courseId){
        CourseFrontVO courseFrontVO = eduCourseService.getFrontCourseInfo(courseId);
        return courseFrontVO;
    }

    @PutMapping("updateCourseInfo")
    @ApiOperation("更新课程信息")
    public Result updateCourseInfo(@RequestBody CourseDTO courseDTO){
        eduCourseService.updateCourseInfo(courseDTO);
        return Result.success();
    }

    @ApiOperation("获取发布课程信息")
    @GetMapping("/getCoursePublish/{courseId}")
    public Result getCoursePublish(@PathVariable String courseId){
        CoursePublishVo coursePublish = eduCourseService.getCoursePublish(courseId);
        return Result.success(coursePublish);
    }

    @ApiOperation("确定发布课程")
    @PutMapping("{courseId}")
    public Result fixCoursePublish(@PathVariable String courseId){
        EduCourse course = eduCourseService.getById(courseId);
        course.setStatus("Normal");
        boolean flag = eduCourseService.updateById(course);
        return flag == true ? Result.success() : Result.error();
    }

    @ApiOperation("分页程信息")
    @GetMapping("/getCourse/{current}/{limit}")
    public Result getCourseInfo(@PathVariable Long current,@PathVariable Long limit){
        // 创建page对象
        Page<EduCourse> eduCoursePage = new Page<>(current,limit);
        // 调用方法实现分页
        eduCourseService.page(eduCoursePage,null);

        // 获取总记录数
        long total = eduCoursePage.getTotal();
        List<EduCourse> courseList = eduCoursePage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",courseList);
        return Result.success(map);
    }

    @ApiOperation("多条件分页程信息")
    @PostMapping("/getCourse/{current}/{limit}")
    public Result getCourseInfoCondition(@PathVariable Long current,@PathVariable Long limit, @RequestBody(required = false) CourseVO courseVO){
        // 创建Page对象
        Page<EduCourse> eduCoursePage = new Page<>(current,limit);
        // 使用方法分页
        eduCourseService.pageQuery(eduCoursePage,courseVO);

        // 获取总记录
        long total = eduCoursePage.getTotal();
        List<EduCourse> courseList = eduCoursePage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",courseList);
        return Result.success(map);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/deleteCourseByCourseId/{courseId}")
    public Result deleteCourse(@PathVariable String courseId){
        boolean flag = eduCourseService.deleteCourse(courseId);
        return flag == true ? Result.success() : Result.error();
    }
}
