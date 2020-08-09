package com.lsy.service_edu.client;

import com.lsy.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname VodClient
 * @Description 微服务调用Vod的服务
 * @Date 2020/08/09 16:38
 */
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping("/vodservice/video/deleteAliVideo/{videoSourceId}")
    Result deleteAliVideo(@PathVariable("videoSourceId") String videoSourceId);

    @DeleteMapping("/vodservice/video/deleteAliVideoList")
    Result deleteAliVideoList(@RequestParam("videoSourceIdList")List<String> videoSourceIdList);
}
