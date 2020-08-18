package com.lsy.service_acl.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_acl.entity.AclPermission;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
public interface AclPermissionService extends IService<AclPermission> {
    /**
     * 获取全部菜单
     * @return
     */
    List<AclPermission> queryAllMenu();

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<AclPermission> selectAllMenu(String roleId);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    /**
     * 递归删除菜单
     * @param id
     */
    void removeChildById(String id);

    /**
     * 根据用户id获取用户菜单
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    /**
     * 获取全部菜单
     * @return
     */
    List<AclPermission> queryAllMenuGuli();

    /**
     * 递归删除菜单
     * @param id
     */
    void removeChildByIdGuli(String id);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
}
