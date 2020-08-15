package com.lsy.service_statistics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author : Lo Shu-ngan
 * @Classname MemberClient
 * @Description 调用会员服务
 * @Date 2020/08/16 02:12
 */
@Component
@FeignClient("service-ucenter")
public interface MemberClient {
    @GetMapping(value = "/ucenterservice/member/countregister/{day}")
    int registerCount(@PathVariable("day") String day);
}
