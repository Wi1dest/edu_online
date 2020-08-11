package com.lsy.service_sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lsy.service_sms.service.SmsService;
import com.lsy.service_sms.utils.ConstantPropertiesUtil;
import com.lsy.service_sms.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @Author : Lo Shu-ngan
 * @Classname SmsServiceImpl
 * @Description 短信发送验证码实现类
 * @Date 2020/08/11 15:42
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean sendCode(String phone) {
        // 判断phone是否为空
        if(StringUtils.isEmpty(phone)) return false;
        // 随机生成6位验证码
        String code = RandomUtil.getSixBitRandom();
        // 存入redis
        redisTemplate.opsForValue().set(phone,code);
        // 开始发送短信
        DefaultProfile profile =
                DefaultProfile.getProfile("default", ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", ConstantPropertiesUtil.ACCESS_KEY_SIGNNAME);
        request.putQueryParameter("TemplateCode", ConstantPropertiesUtil.ACCESS_KEY_TEMPLATECODE);
        // 阿里规定验证码需要用JSON格式传入
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
