package com.lsy.service_edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author : Lo Shu-ngan
 * @Classname OrderClient
 * @Description 调用order模块
 * @Date 2020/08/15 20:33
 */
@Component
@FeignClient(name = "service-order")
public interface OrderClient {
    @GetMapping("/orderservice/order/checkPayStatusByCourseIdAndMemberId/{courseId}/{memberId}")
    boolean checkPayStatusByCourseIdAndMemberId(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
