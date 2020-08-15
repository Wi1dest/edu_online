package com.lsy.common.enums;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname UcenterExceptionCode
 * @Description 用户异常枚举
 * @Date 2020/08/05 17:53
 */
@Getter
public enum UcenterExceptionCode {
    MOBILE_PASSWORD_NULL(44000,"账号或密码为空!"),
    MEMBER_PASSWORD_ERROR(44001,"密码错误!"),
    MEMBER_IS_BAN(44002,"用户被禁用!"),
    REGISTER_FROM_NO_FULL(44003,"注册参数不完整!"),
    CODE_IS_ERROR(44004,"验证码错误!"),
    MOBILE_IS_REGISTER(44005,"该手机号已被注册!"),
    MOBILE_NOT_FOUND(44006,"查无此人!")
    ;
    private Integer code;

    private String msg;

    UcenterExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
