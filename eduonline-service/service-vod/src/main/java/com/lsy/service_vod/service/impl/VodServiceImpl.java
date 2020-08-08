package com.lsy.service_vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.lsy.service_vod.service.VodService;
import com.lsy.service_vod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author : Lo Shu-ngan
 * @Classname VodServiceImpl
 * @Description 视频接口实现类
 * @Date 2020/08/08 21:24
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = multipartFile.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
