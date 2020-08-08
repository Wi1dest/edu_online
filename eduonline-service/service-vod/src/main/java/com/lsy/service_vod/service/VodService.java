package com.lsy.service_vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : Lo Shu-ngan
 * @Classname VodService
 * @Description 视频接口
 * @Date 2020/08/08 21:22
 */
public interface VodService {
    /**
     * 视频上传到阿里
     * @param multipartFile
     */
    String uploadVideo(MultipartFile multipartFile);
}
