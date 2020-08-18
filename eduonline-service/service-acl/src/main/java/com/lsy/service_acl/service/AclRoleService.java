package com.lsy.service_acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_acl.entity.AclRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
public interface AclRoleService extends IService<AclRole> {
    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * 根据用户分配角色
     * @param userId
     * @param roleId
     */
    void saveUserRoleRealtionShip(String userId, String[] roleId);

    List<AclRole> selectRoleByUserId(String id);
}
