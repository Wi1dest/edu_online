package com.lsy.service_edu.client;

import com.lsy.common.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname VodFileDegradeFeignClient
 * @Description 熔断机制实现类
 * @Date 2020/08/10 03:01
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result deleteAliVideo(String videoSourceId) {
        return Result.error("触发熔断机制,视频删除失败!");
    }

    @Override
    public Result deleteAliVideoList(List<String> videoSourceIdList) {
        return Result.error("触发熔断机制,视频删除失败!");
    }
}
