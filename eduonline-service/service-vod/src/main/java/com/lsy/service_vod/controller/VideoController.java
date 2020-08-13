package com.lsy.service_vod.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Lo Shu-ngan
 * @Classname VideoController
 * @Description 视频播放控制器
 * @Date 2020/08/14 02:22
 */
@RestController
@RequestMapping("/vodservice/video")
@CrossOrigin
@Api(tags = "阿里视频在线播放")
public class VideoController {
    @Autowired
    private VodService vodService;

    @ApiOperation("获取视频播放凭证")
    @GetMapping("get-play-auth/{videoId}")
    public Result getVideoPlayAuth(@PathVariable String videoId) {
        String playAuth = vodService.getVideoPlayAuth(videoId);
        return Result.success(playAuth);
    }
}
