package com.lsy.service_edu.service;

import com.lsy.service_edu.dto.CourseDTO;
import com.lsy.service_edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 添加课程基本信息
     * @param courseDTO 课程DTO类
     */
    String saveCourse(CourseDTO courseDTO);
}
