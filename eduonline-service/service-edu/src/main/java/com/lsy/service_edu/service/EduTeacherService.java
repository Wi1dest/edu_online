package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.service_edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.vo.TeacherVO;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 多条件分页查询讲师列表
     * @param teacherPage page对象
     * @param teacherVO 多条件查询构造对象
     */
    void pageQuery(Page<EduTeacher> teacherPage, TeacherVO teacherVO);
}
