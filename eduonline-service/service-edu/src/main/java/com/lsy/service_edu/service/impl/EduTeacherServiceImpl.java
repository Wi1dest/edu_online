package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_edu.entity.EduTeacher;
import com.lsy.service_edu.mapper.EduTeacherMapper;
import com.lsy.service_edu.service.EduTeacherService;
import com.lsy.service_edu.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
