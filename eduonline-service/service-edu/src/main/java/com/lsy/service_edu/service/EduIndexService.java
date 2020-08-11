package com.lsy.service_edu.service;

import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduIndexService
 * @Description 首页接口
 * @Date 2020/08/11 14:16
 */
public interface EduIndexService {
    /**
     * 获取首页信息
     * @return
     */
    Map<String,Object> getIndexInfo();
}
