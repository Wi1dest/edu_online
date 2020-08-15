package com.lsy.service_ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.common.exception.UcenterException;
import com.lsy.common.utils.JwtUtils;
import com.lsy.common.utils.MD5Util;
import com.lsy.service_ucenter.entity.UcenterMember;
import com.lsy.service_ucenter.entity.dto.MemberDto;
import com.lsy.service_ucenter.entity.vo.MemberVo;
import com.lsy.service_ucenter.mapper.UcenterMemberMapper;
import com.lsy.service_ucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.lsy.common.enums.UcenterExceptionCode.*;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-11
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new UcenterException(MOBILE_PASSWORD_NULL);
        }
        // 根据手机号码获取会员信息
        QueryWrapper<UcenterMember> wrapperMember = new QueryWrapper<>();
        wrapperMember.eq("mobile",mobile);
        UcenterMember dbMember = baseMapper.selectOne(wrapperMember);
        if (!MD5Util.encrypt(password).equals(dbMember.getPassword())){
            throw new UcenterException(MEMBER_PASSWORD_ERROR);
        }

        // 判断用户是否禁用
        if (dbMember.getIsDisabled()){
            throw new UcenterException(MEMBER_IS_BAN);
        }

        // 生成JWT凭证
        String token = JwtUtils.getJwtToken(dbMember.getId(), dbMember.getNickname());
        return token;
    }

    @Override
    public void register(MemberDto memberDto) {
        String mobile = memberDto.getMobile();
        String nickname = memberDto.getNickname();
        String code = memberDto.getCode();
        String password = memberDto.getPassword();
        // 判空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code) || StringUtils.isEmpty(password)){
            throw new UcenterException(REGISTER_FROM_NO_FULL);
        }
        // 判断验证码是否正确
        String codeFromRedis = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(codeFromRedis)){
            throw new UcenterException(CODE_IS_ERROR);
        }

        // 检查该手机号码是否被注册
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count.intValue() > 0){
            throw new UcenterException(MOBILE_IS_REGISTER);
        }

        UcenterMember ucenterMember = new UcenterMember();;
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5Util.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setAvatar("https://eduonline-project.oss-cn-shenzhen.aliyuncs.com/9.jpg");
        baseMapper.insert(ucenterMember);
    }

    @Override
    public MemberVo getMemberInfoFromRequest(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByRequest(request);
        UcenterMember ucenterMember = baseMapper.selectById(memberId);
        if (ucenterMember == null){
            throw new UcenterException(MOBILE_NOT_FOUND);
        }
        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(ucenterMember,memberVo);
        return memberVo;
    }

    @Override
    public MemberVo getMemberInfoFromToken(String token) {
        String memberId = JwtUtils.getMemberIdByJwtToken(token);
        UcenterMember ucenterMember = baseMapper.selectById(memberId);
        if (ucenterMember == null){
            throw new UcenterException(MOBILE_NOT_FOUND);
        }
        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(ucenterMember,memberVo);
        return memberVo;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        int count = baseMapper.selectRegisterCount(day);
        return count;
    }
}
