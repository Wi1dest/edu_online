package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.entity.EduTeacher;
import com.lsy.service_edu.service.EduCourseService;
import com.lsy.service_edu.service.EduIndexService;
import com.lsy.service_edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduIndexServiceImpl
 * @Description 首页接口实现类
 * @Date 2020/08/11 14:17
 */
@Service
public class EduIndexServiceImpl implements EduIndexService {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @Cacheable(value = "index", key = "'selectIndexList'")
    @Override
    public Map<String, Object> getIndexInfo() {
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.orderByDesc("id").last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapperCourse);
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id").last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        Map<String,Object> map = new HashMap<>();
        map.put("courseList",courseList);
        map.put("teacherList",teacherList);
        return map;
    }
}
