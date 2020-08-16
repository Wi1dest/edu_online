package com.lsy.service_order.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_order.service.EduPayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-15
 */
@RestController
@RequestMapping("/orderservice/log")
@Api(tags = "微信支付模块")
public class EduPayLogController {
    @Autowired
    private EduPayLogService payLogService;

    @GetMapping("createWeChatQRCode/{orderNo}")
    @ApiOperation("创建支付二维码")
    public Result createWeChatPayQRCode(@PathVariable String orderNo) {
        Map map = payLogService.createPayQRCode(orderNo);
        return Result.success(map);
    }

    @GetMapping("checkPayStatus/{orderNo}")
    @ApiOperation("查看支付状态")
    public Result checkPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.checkPayStatus(orderNo);
        if (map == null) {
            return Result.error("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return Result.success("支付成功");
        }

        return Result.error(25000,"支付中");
    }
}

