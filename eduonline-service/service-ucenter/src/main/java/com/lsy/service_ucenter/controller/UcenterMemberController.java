package com.lsy.service_ucenter.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_ucenter.entity.UcenterMember;
import com.lsy.service_ucenter.entity.dto.MemberDto;
import com.lsy.service_ucenter.entity.vo.MemberVo;
import com.lsy.service_ucenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/ucenterservice/member")
@CrossOrigin
@Api(tags = "用户管理模块")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result registerMember(@RequestBody MemberDto memberDto){
        memberService.register(memberDto);
        return Result.success();
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result loginMember(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        return Result.success(token);
    }

    @ApiOperation("根据Token获取用户信息")
    @GetMapping("auth/getMemberInfo")
    public Result getMemberInfoFromToken(HttpServletRequest request){
        MemberVo memberVo = memberService.getMemberInfoFromRequest(request);
        return Result.success(memberVo);
    }
}

