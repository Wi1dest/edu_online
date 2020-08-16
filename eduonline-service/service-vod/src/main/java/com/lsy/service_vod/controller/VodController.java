package com.lsy.service_vod.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname VodController
 * @Description Vod控制器
 * @Date 2020/08/08 21:19
 */
@RestController
@RequestMapping("/vodservice/video")
@Api(tags = "上传视频到阿里云模块")
public class VodController {
    @Autowired
    private VodService vodService;


    @PostMapping("uploadAliVideo")
    @ApiOperation("视频上传到阿里云")
    public Result uploadAliVideo(MultipartFile file){
        String videoUrl = vodService.uploadVideo(file);
        return Result.success(videoUrl);
    }

    @DeleteMapping("deleteAliVideo/{videoSourceId}")
    @ApiOperation("删除阿里云上的视频")
    public Result deleteAliVideo(@PathVariable String videoSourceId){
        vodService.removeVideo(videoSourceId);
        return Result.success();
    }

    @DeleteMapping("deleteAliVideoList")
    @ApiOperation("删除多个阿里云上的视频")
    public Result deleteAliVideoList(@RequestParam("videoSourceIdList")List<String> videoSourceIdList){
        vodService.removeVideoList(videoSourceIdList);
        return Result.success();
    }

    @GetMapping("get-play-auth/{videoId}")
    @ApiOperation("获取视频播放凭证")
    public Result getVideoPlayAuth(@PathVariable String videoId) {
        String playAuth = vodService.getVideoPlayAuth(videoId);
        return Result.success(playAuth);
    }
}
