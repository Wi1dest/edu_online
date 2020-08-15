package com.lsy.service_order.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author : Lo Shu-ngan
 * @Classname ConstantPropertiesUtil
 * @Description order常量类
 * @Date 2020/08/04 18:14
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${wechat.pay.appid}")
    private String weChatAppId;

    @Value("${wechat.pay.mchid}")
    private String weChatMchId;

    @Value("${wechat.pay.partnerkey}")
    private String partnerKey;


    public static String WECHAT_PAY_APP_ID;
    public static String WECHAT_PAY_MCH_ID;
    public static String WECHAT_PAY_PARTNER_KEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        WECHAT_PAY_APP_ID = weChatAppId;
        WECHAT_PAY_MCH_ID = weChatMchId;
        WECHAT_PAY_PARTNER_KEY = partnerKey;
    }
}
