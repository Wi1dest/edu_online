package com.lsy.service_acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.service_acl.entity.AclPermission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
public interface AclPermissionMapper extends BaseMapper<AclPermission> {
    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<AclPermission> selectPermissionByUserId(String userId);
}
