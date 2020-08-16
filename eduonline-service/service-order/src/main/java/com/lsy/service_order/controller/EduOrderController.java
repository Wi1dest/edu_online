package com.lsy.service_order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsy.common.utils.Result;
import com.lsy.service_order.entity.EduOrder;
import com.lsy.service_order.service.EduOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-15
 */
@RestController
@RequestMapping("/orderservice/order")
@Api(tags = "订单模块")
public class EduOrderController {
    @Autowired
    private EduOrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo = orderService.createOrder(courseId,request);
        return Result.success(orderNo);
    }

    @ApiOperation("根据订单NO获取订单信息")
    @GetMapping("getOrder/{orderId}")
    public Result getOrderInfoByOrderNO(@PathVariable String orderId) {
        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        EduOrder order = orderService.getOne(wrapper);
        return Result.success(order);
    }

    @ApiOperation("根据课程ID和用户ID获取订单信息")
    @GetMapping("checkPayStatusByCourseIdAndMemberId/{courseId}/{memberId}")
    public boolean checkPayStatusByCourseIdAndMemberId(@PathVariable String courseId,@PathVariable String memberId) {
        boolean flag = orderService.checkPayStatus(courseId,memberId);
        return flag == true ? true : false;
    }
}

