package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.dto.CourseDTO;
import com.lsy.service_edu.dto.front.CourseFrontQueryDTO;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.vo.CourseVO;
import com.lsy.service_edu.vo.course.CoursePublishVo;

import java.util.Map;

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

    /**
     * 多条件分页查询
     * @param eduCoursePage
     * @param courseVO
     */
    void pageQuery(Page<EduCourse> eduCoursePage, CourseVO courseVO);

    /**
     * 根据课程ID删除课程(包含删除章节与小节)
     * @param courseId
     * @return
     */
    boolean deleteCourse(String courseId);

    /**
     * 前端查询课程列表(有查询条件)
     * @param coursePage
     * @param courseFrontDTO
     * @return
     */
    Map<String, Object> getCoursePageList(Page<EduCourse> coursePage, CourseFrontQueryDTO courseFrontDTO);
}
