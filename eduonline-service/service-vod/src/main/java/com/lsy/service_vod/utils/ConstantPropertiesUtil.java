package com.lsy.service_vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author : Lo Shu-ngan
 * @Classname ConstantPropertiesUtil
 * @Description vod常量类
 * @Date 2020/08/04 18:14
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    //InitializingBean 初始化后执行 afterPropertiesSet方法 ,把私有的成员变量赋值给静态变量供外界使用
    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;


    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;
    }
}
