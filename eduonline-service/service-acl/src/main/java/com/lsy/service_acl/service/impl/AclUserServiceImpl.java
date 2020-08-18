package com.lsy.service_acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_acl.entity.AclUser;
import com.lsy.service_acl.mapper.AclUserMapper;
import com.lsy.service_acl.service.AclUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {
    @Override
    public AclUser selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<AclUser>().eq("username", username));
    }
}
