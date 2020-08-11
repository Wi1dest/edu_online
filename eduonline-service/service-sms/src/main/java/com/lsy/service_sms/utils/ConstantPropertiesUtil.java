package com.lsy.service_sms.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author : Lo Shu-ngan
 * @Classname ConstantPropertiesUtil
 * @Description TODO
 * @Date 2020/08/11 15:49
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    //InitializingBean 初始化后执行 afterPropertiesSet方法 ,把私有的成员变量赋值给静态变量供外界使用

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.SignName}")
    private String signName;

    @Value("${aliyun.oss.file.TemplateCode}")
    private String templateCode;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_KEY_SIGNNAME;
    public static String ACCESS_KEY_TEMPLATECODE;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;
        ACCESS_KEY_SIGNNAME = signName;
        ACCESS_KEY_TEMPLATECODE = templateCode;
    }
}
