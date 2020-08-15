package com.lsy.service_order.client;

import com.lsy.service_edu.vo.CourseFrontVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author : Lo Shu-ngan
 * @Classname CourseClient
 * @Description 课程类client
 * @Date 2020/08/15 00:43
 */
@Component
@FeignClient(name = "service-edu")
public interface CourseClient {
    @GetMapping("/eduservice/course/getCourseInfoOnService/{courseId}")
    CourseFrontVO getCourseInfoOnService(@PathVariable("courseId") String courseId);
}
