package com.lsy.service_oss.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname OssController
 * @Description 阿里OSS上传控制器
 * @Date 2020/08/04 18:21
 */
@RestController
@Api(tags = "OSS上传控制器")
@RequestMapping("/ossservice/file")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation("头像上传")
    @PostMapping
    public Result uploadOssFile(MultipartFile file){
        // 获取上传文件MultipartFile
        String avatarUrl = ossService.uploadFileAvatar(file);
        Map<String,String> map = new HashMap<>();
        map.put("url",avatarUrl);
        return Result.success(map);
    }

    @ApiOperation("Banner上传")
    @PostMapping("banner")
    public Result uploadOssFileBanner(MultipartFile file){
        // 获取上传文件MultipartFile
        String bannerUrl = ossService.uploadFileBanner(file);
        Map<String,String> map = new HashMap<>();
        map.put("url",bannerUrl);
        return Result.success(map);
    }
}
