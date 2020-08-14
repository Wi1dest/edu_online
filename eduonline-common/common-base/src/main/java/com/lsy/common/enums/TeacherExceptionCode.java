package com.lsy.common.enums;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname TeacherExceptionCode
 * @Description 教师类异常枚举
 * @Date 2020/08/02 22:48
 */
@Getter
public enum TeacherExceptionCode {
    TEACHER_NOT_FOUND(40000,"查无此讲师")
    ;
    private Integer code;

    private String msg;

    TeacherExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
