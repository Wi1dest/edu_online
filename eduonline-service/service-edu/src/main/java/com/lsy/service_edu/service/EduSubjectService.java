package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.entity.EduSubject;
import org.springframework.web.multipart.MultipartFile;

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
}
