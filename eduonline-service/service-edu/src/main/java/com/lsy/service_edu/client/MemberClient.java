package com.lsy.service_edu.client;

import com.lsy.service_ucenter.entity.vo.MemberVo;
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
@FeignClient(name = "service-ucenter")
public interface MemberClient {
    @GetMapping("/ucenterservice/member/auth/getMemberInfoByToken/{token}")
    MemberVo getMemberInfoByToken(@PathVariable("token") String token);
}
