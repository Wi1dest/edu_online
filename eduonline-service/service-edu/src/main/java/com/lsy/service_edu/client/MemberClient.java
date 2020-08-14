package com.lsy.service_edu.client;

import com.lsy.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author : Lo Shu-ngan
 * @Classname MemberClient
 * @Description 微服务调用member的服务
 * @Date 2020/08/14 14:47
 */
@Component
@FeignClient(name = "service-ucenter",fallback = MemberFileDegradeFeignClient.class)
public interface MemberClient {
    @GetMapping("/ucenterservice/member/auth/getMemberInfoByToken/{token}")
    Result getMemberInfoByToken(@PathVariable String token);
}
