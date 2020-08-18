package com.lsy.service_acl.service.impl;

import com.lsy.security.entity.SecurityUser;
import com.lsy.security.entity.User;
import com.lsy.service_acl.entity.AclUser;
import com.lsy.service_acl.service.AclPermissionService;
import com.lsy.service_acl.service.AclUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname UserDetailsServiceImpl
 * @Description 自定义userDetailsService - 认证用户详情
 * @Date 2020/08/18 17:35
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AclUserService userService;

    @Autowired
    private AclPermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        AclUser user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user) {
            //throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        User curUser = new User();
        BeanUtils.copyProperties(user, curUser);

        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }
}
