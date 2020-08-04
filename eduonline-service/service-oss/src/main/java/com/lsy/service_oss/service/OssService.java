package com.lsy.service_oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : Lo Shu-ngan
 * @Classname OssService
 * @Description OSS接口
 * @Date 2020/08/04 18:22
 */
public interface OssService {
    /**
     * 上传头像
     * @return 返回头像URL
     */
    String uploadFileAvatar(MultipartFile file);
}
