package com.lsy.common_utils;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname ResuleCodeEnums
 * @Description 返回状态
 * @Date 2020/08/02 14:42
 */
@Getter
public enum ResultCodeEnums {
    SUCCESS(20000,"成功"),
    ERROR(20001,"失败");

    private Integer code;

    private String msg;

    ResultCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
