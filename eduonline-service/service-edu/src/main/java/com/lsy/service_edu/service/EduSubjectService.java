package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.entity.EduSubject;
import com.lsy.service_edu.vo.OneSubjectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-04
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 批量导入
     * @param file excel文件
     */
    void batchImport(MultipartFile file);

    /**
     * 获取分类
     */
    List<OneSubjectVO> getList();
}
