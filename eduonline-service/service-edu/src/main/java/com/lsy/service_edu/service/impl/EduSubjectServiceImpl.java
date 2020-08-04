package com.lsy.service_edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.exception.ExcelException;
import com.lsy.service_edu.entity.EduSubject;
import com.lsy.service_edu.entity.excel.ExcelSubjectData;
import com.lsy.service_edu.entity.listener.SubjectExcelListener;
import com.lsy.service_edu.mapper.EduSubjectMapper;
import com.lsy.service_edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
}
