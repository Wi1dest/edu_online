package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.dto.CourseDTO;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.vo.course.CoursePublishVo;

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

    /**
     * 根据课程ID查询课程信息
     * @param courseId
     * @return
     */
    CourseDTO getCourseInfo(String courseId);

    /**
     * 根据课程ID 更新
     * @param courseDTO
     */
    void updateCourseInfo(CourseDTO courseDTO);

    /**
     * 根据课程ID获取发布课程信息
     * @param courseId
     * @return
     */
    CoursePublishVo getCoursePublish(String courseId);
}
