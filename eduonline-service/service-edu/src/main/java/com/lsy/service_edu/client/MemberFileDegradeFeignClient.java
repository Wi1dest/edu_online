package com.lsy.service_edu.client;

import com.lsy.common.utils.Result;
import org.springframework.stereotype.Component;

/**
 * @Author : Lo Shu-ngan
 * @Classname MemberFileDegradeFeignClient
 * @Description 熔断机制实现类
 * @Date 2020/08/14 14:48
 */
@Component
public class MemberFileDegradeFeignClient implements MemberClient {

    @Override
    public Result getMemberInfoByToken(String token) {
        return Result.error("触发熔断机制,获取登录会员信息失败!");

    }
}
