package com.lsy.service_acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_acl.entity.AclRole;
import com.lsy.service_acl.entity.AclUserRole;
import com.lsy.service_acl.mapper.AclRoleMapper;
import com.lsy.service_acl.service.AclRoleService;
import com.lsy.service_acl.service.AclUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
@Service
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {
    @Autowired
    private AclUserRoleService userRoleService;


    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        //查询所有的角色
        List<AclRole> allRolesList =baseMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<AclUserRole> existUserRoleList = userRoleService.list(new QueryWrapper<AclUserRole>().eq("user_id", userId).select("role_id"));

        List<String> existRoleList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());

        //对角色进行分类
        List<AclRole> assignRoles = new ArrayList<AclRole>();
        for (AclRole role : allRolesList) {
            //已分配
            if(existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    /**
     * 根据用户分配角色
     * @param userId
     * @param roleIds
     */
    @Override
    public void saveUserRoleRealtionShip(String userId, String[] roleIds) {
        userRoleService.remove(new QueryWrapper<AclUserRole>().eq("user_id", userId));

        List<AclUserRole> userRoleList = new ArrayList<>();
        for(String roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) {
                continue;
            }
            AclUserRole userRole = new AclUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public List<AclRole> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<AclUserRole> userRoleList = userRoleService.list(new QueryWrapper<AclUserRole>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<AclRole> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
