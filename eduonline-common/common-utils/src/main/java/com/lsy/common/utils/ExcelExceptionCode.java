package com.lsy.common.utils;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname ExcelExceptionCode
 * @Description excel异常枚举
 * @Date 2020/08/04 23:28
 */
@Getter
public enum ExcelExceptionCode {
    EXCEL_NOT_FOUND(41000,"excel添加失败!"),
    SUBJECT_ADD_ERROR(41001,"分类添加失败!")
    ;
    private Integer code;

    private String msg;

    ExcelExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
