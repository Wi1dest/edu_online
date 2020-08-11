package com.lsy.service_ucenter.service;

import com.lsy.service_ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_ucenter.entity.dto.MemberDto;
import com.lsy.service_ucenter.entity.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-11
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 用户登录
     * @param ucenterMember
     * @return
     */
    String login(UcenterMember ucenterMember);

    /**
     * 用户注册
     * @param memberDto
     */
    void register(MemberDto memberDto);

    /**
     * 通过request中的token信息获取用户信息
     * @param request
     * @return
     */
    MemberVo getMemberInfoFromRequest(HttpServletRequest request);
}
