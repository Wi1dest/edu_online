package com.lsy.service_sms.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Lo Shu-ngan
 * @Classname SenMessageController
 * @Description 发送短信控制器
 * @Date 2020/08/11 15:37
 */
@CrossOrigin
@RequestMapping("/smsservice/sms")
@RestController
@Api(tags = "阿里短信模块")
public class SenMessageController {
    @Autowired
    private SmsService smsService;

    @GetMapping("getPhoneCode/{phone}")
    @ApiOperation("获取短信验证码")
    public Result getPhoneCode(@PathVariable String phone){
        boolean flag = smsService.sendCode(phone);
        return flag == true ? Result.success("短信发送成功!") : Result.error("短信发送失败!");
    }
}
