package com.lsy.service_vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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

    /**
     * 删除视频
     * @param videoId
     */
    void removeVideo(String videoId);

    /**
     * 删除多个视频
     * @param videoIdList
     */
    void removeVideoList(List<String> videoIdList);
}
