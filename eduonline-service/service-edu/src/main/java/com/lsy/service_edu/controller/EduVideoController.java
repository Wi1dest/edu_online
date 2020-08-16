package com.lsy.service_edu.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_edu.client.VodClient;
import com.lsy.service_edu.entity.EduVideo;
import com.lsy.service_edu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduVideoController
 * @Description 小节控制器
 * @Date 2020/08/07 17:34
 */
@RestController
@RequestMapping("/eduservice/video")
@Api(tags = "小节控制器")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加小节")
    @PostMapping("saveChapter")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        boolean flag = eduVideoService.save(eduVideo);
        return flag == true ? Result.success() : Result.error();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("{videoId}")
    public Result deleteVideoByVideoId(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        if (!StringUtils.isEmpty(video.getVideoSourceId())){
            vodClient.deleteAliVideo(video.getVideoSourceId());
        }
        boolean flag = eduVideoService.removeById(videoId);
        return flag == true ? Result.success() : Result.error();
    }

    @ApiOperation("根据小节ID查询小节信息")
    @GetMapping("getVideo/{videoId}")
    public Result getVideoByVideoId(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return Result.success(video);
    }

    @ApiOperation("根据小节ID更新小节信息")
    @PutMapping("update")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        boolean flag = eduVideoService.updateById(eduVideo);
        return flag == true ? Result.success() : Result.error();
    }
}
