package com.lsy.service_sms.service;

/**
 * @Author : Lo Shu-ngan
 * @Classname SmsService
 * @Description 发送短信接口
 * @Date 2020/08/11 15:35
 */
public interface SmsService {
    /**
     * 发送短信验证码
     * @return
     */
    boolean sendCode(String phone);
}
