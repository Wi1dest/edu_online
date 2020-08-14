package com.lsy.common.exception;

import com.lsy.common.enums.TeacherExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduTeacherException
 * @Description 教师类异常
 * @Date 2020/08/02 22:46
 */
@Data
public class EduTeacherException extends RuntimeException {
    private Integer code;

    private String msg;

    public EduTeacherException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public EduTeacherException(TeacherExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.msg = exceptionCode.getMsg();
    }
}
