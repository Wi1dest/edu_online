package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.entity.EduTeacher;
import com.lsy.service_edu.mapper.EduTeacherMapper;
import com.lsy.service_edu.service.EduCourseService;
import com.lsy.service_edu.service.EduTeacherService;
import com.lsy.service_edu.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Autowired
    private EduCourseService eduCourseService;


    @Override
    public void pageQuery(Page<EduTeacher> teacherPage, TeacherVO teacherVO) {
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //多条件组合查询
        String name = teacherVO.getName();
        Integer level = teacherVO.getLevel();
        String begin = teacherVO.getBegin();
        String end = teacherVO.getEnd();

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        wrapper.orderByDesc("gmt_create");

        // 调用方法实现分页
        eduTeacherMapper.selectPage(teacherPage, wrapper);
    }

    @Override
    public Map<String, Object> getTeacherPage(Page<EduTeacher> page) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        baseMapper.selectPage(page,wrapper);

        List<EduTeacher> teacherList = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", teacherList);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public Map<String, Object> getTeacherInfo(String id) {
        EduTeacher eduTeacher = eduTeacherMapper.selectById(id);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",eduTeacher.getId());
        List<EduCourse> courseList = eduCourseService.list(wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher",eduTeacher);
        map.put("courseList",courseList);
        return map;
    }
}
