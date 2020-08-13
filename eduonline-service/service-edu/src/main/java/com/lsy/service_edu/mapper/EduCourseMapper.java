package com.lsy.service_edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.vo.CourseFrontVO;
import com.lsy.service_edu.vo.course.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getCoursePublishInfo(String courseId);

    CourseFrontVO selectFrontCourseInfo(String courseId);
}
