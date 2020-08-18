package com.lsy.service_acl.service;

import com.lsy.service_acl.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
public interface AclUserService extends IService<AclUser> {
    /**
     * 从数据库中取出用户信息
     * @param username
     * @return
     */
    AclUser selectByUsername(String username);
}
