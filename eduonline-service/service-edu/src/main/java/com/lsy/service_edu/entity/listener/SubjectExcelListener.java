package com.lsy.service_edu.entity.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsy.common.utils.ExcelExceptionCode;
import com.lsy.exception.ExcelException;
import com.lsy.service_edu.entity.EduSubject;
import com.lsy.service_edu.entity.excel.ExcelSubjectData;
import com.lsy.service_edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : Lo Shu-ngan
 * @Classname SubjectExcelListener
 * @Description Excel监听器
 * @Date 2020/08/04 23:00
 */
@Component
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 一行一行去读取excle内容
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new ExcelException(ExcelExceptionCode.EXCEL_NOT_FOUND);
        }
        // 检查一级分类是否存在 如果不存在就添加一级分类
        EduSubject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName());
         // existOneSubject 为空说明这个一级分类不存在 可以添加进库
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

        // 获取一级分类的ID , 因为一级分类ID就是二级分类的父类ID
        String pid = existOneSubject.getId();

        // 检查二级分类是否存在 如果不存在就添加二级分类
        EduSubject twoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), pid);
        // twoSubject 为空说明这个二级分类不存在 可以添加进库
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(pid);
            eduSubjectService.save(twoSubject);
        }
    }

    /**
     * 读取完成后执行
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 检查一级分类是否存在
     * @param name
     * @return
     */
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    /**
     * 检查二级分类是否存在
     * @param name
     * @param pid
     * @return
     */
    private EduSubject existTwoSubject(String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }
}
