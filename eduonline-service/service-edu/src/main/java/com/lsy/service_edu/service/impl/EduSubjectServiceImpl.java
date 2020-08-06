package com.lsy.service_edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.exception.ExcelException;
import com.lsy.service_edu.entity.EduSubject;
import com.lsy.service_edu.entity.excel.ExcelSubjectData;
import com.lsy.service_edu.entity.listener.SubjectExcelListener;
import com.lsy.service_edu.mapper.EduSubjectMapper;
import com.lsy.service_edu.service.EduSubjectService;
import com.lsy.service_edu.vo.subject.OneSubjectVO;
import com.lsy.service_edu.vo.subject.TwoSubjectVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.lsy.common.utils.ExcelExceptionCode.SUBJECT_ADD_ERROR;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-04
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private SubjectExcelListener listener;

    @Override
    public void batchImport(MultipartFile file) {
        try {
            // 获取文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, ExcelSubjectData.class,listener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException(SUBJECT_ADD_ERROR);
        }
    }

    @Override
    public List<OneSubjectVO> getList() {
        // 查询一级目录
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(oneWrapper);
        // 查询二级目录
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoWrapper);
        // 封装List<OneSubjectVO>
        List<OneSubjectVO> list = new ArrayList<>();

        for (EduSubject oneSubject : oneSubjectList) {
            // 处理一级分类 (设置一级分类里的 ID 和 一级分类名)
            OneSubjectVO oneSubjectVO = new OneSubjectVO();
            BeanUtils.copyProperties(oneSubject,oneSubjectVO);

            // 处理二级分类
            List<TwoSubjectVO> twoList = new ArrayList<>();
            for (EduSubject twoSubject : twoSubjectList) {
                TwoSubjectVO twoSubjectVO = new TwoSubjectVO();
                if (oneSubject.getId().equals(twoSubject.getParentId())){
                    BeanUtils.copyProperties(twoSubject,twoSubjectVO);
                    twoList.add(twoSubjectVO);
                }
            }

            // 把二级分类集合置入一级分类对象里的children
            oneSubjectVO.setChildren(twoList);

            // 把整个一级分类(到此处时,一级分类对象里已经包含二级分类集合在内)加入list集合
            list.add(oneSubjectVO);

        }

        return list;
    }
}
