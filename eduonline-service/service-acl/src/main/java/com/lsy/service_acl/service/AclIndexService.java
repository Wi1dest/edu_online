package com.lsy.service_acl.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname AclIndexService
 * @Description 权限index接口
 * @Date 2020/08/17 19:55
 */
public interface AclIndexService {
    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单
     * @param username
     * @return
     */
    List<JSONObject> getMenu(String username);
}
