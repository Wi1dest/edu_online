package com.lsy.service_vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.lsy.exception.AliVodException;
import com.lsy.service_vod.service.VodService;
import com.lsy.service_vod.utils.ConstantPropertiesUtil;
import com.lsy.service_vod.utils.InitVodCilent;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.lsy.common.utils.AliVodExceptionCode.DELETE_VOD_ERROR;
import static com.lsy.common.utils.AliVodExceptionCode.UPLOAD_VOD_ERROR;

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
            throw new AliVodException(UPLOAD_VOD_ERROR);
        }
    }

    @Override
    public void removeVideo(String videoId) {
        try {
            //初始化对象
            DefaultAcsClient acsClient = InitVodCilent.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoId);
            //调用初始化对象的方法实现删除
            acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new AliVodException(DELETE_VOD_ERROR);
        }
    }
}
