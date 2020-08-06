package com.lsy.common.utils;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname CourseExceptionCode
 * @Description 课程异常枚举
 * @Date 2020/08/05 17:53
 */
@Getter
public enum  CourseExceptionCode {
    INSERT_COURSE_ERROR(42000,"课程添加失败!"),
    UPDATE_COURSE_ERROR(42001,"课程更新失败!")
    ;
    private Integer code;

    private String msg;

    CourseExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
