package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.common.utils.CourseExceptionCode;
import com.lsy.exception.EduCourseException;
import com.lsy.service_edu.dto.CourseDTO;
import com.lsy.service_edu.entity.EduChapter;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.entity.EduCourseDescription;
import com.lsy.service_edu.entity.EduVideo;
import com.lsy.service_edu.mapper.EduCourseMapper;
import com.lsy.service_edu.service.EduChapterService;
import com.lsy.service_edu.service.EduCourseDescriptionService;
import com.lsy.service_edu.service.EduCourseService;
import com.lsy.service_edu.service.EduVideoService;
import com.lsy.service_edu.vo.CourseVO;
import com.lsy.service_edu.vo.course.CoursePublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.lsy.common.utils.CourseExceptionCode.UPDATE_COURSE_ERROR;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;



    @Override
    public String saveCourse(CourseDTO courseDTO) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseDTO,eduCourse);
        int result = baseMapper.insert(eduCourse);
        if (result <= 0){
            throw new EduCourseException(CourseExceptionCode.INSERT_COURSE_ERROR);
        }

        // 由于课程ID和课程介绍ID应该是一致的,所以在此先获取课程ID
        String id = eduCourse.getId();

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseDTO.getDescription());
        // 将课程ID赋给课程介绍ID,建立两表的关联
        eduCourseDescription.setId(id);
        eduCourseDescriptionService.save(eduCourseDescription);

        return id;
    }

    @Override
    public CourseDTO getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(eduCourse,courseDTO);
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseDTO.setDescription(courseDescription.getDescription());
        return courseDTO;
    }

    @Override
    public void updateCourseInfo(CourseDTO courseDTO) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseDTO,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i<=0){
            throw new EduCourseException(UPDATE_COURSE_ERROR);
        }

        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseDTO.getId());
        courseDescription.setDescription(courseDTO.getDescription());
        eduCourseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublish(String courseId) {
        CoursePublishVo coursePublishInfo = eduCourseMapper.getCoursePublishInfo(courseId);
        return coursePublishInfo;
    }

    @Override
    public void pageQuery(Page<EduCourse> eduCoursePage, CourseVO courseVO) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String status = courseVO.getStatus();
        String title = courseVO.getTitle();
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        wrapper.orderByDesc("gmt_modified");
        eduCourseMapper.selectPage(eduCoursePage,wrapper);
    }

    @Override
    public boolean deleteCourse(String courseId) {
        // 先查小节再删除小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        eduVideoService.remove(wrapperVideo);

        // 查章节删除章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        eduChapterService.remove(wrapperChapter);

        // 删除该课程的简介
        eduCourseDescriptionService.removeById(courseId);

        // 删除该课程
        int i = eduCourseMapper.deleteById(courseId);

        return i > 0 ? true : false;
    }
}
