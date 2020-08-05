package com.lsy.exception;

import com.lsy.common.utils.CourseExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduCourseException
 * @Description 课程异常类
 * @Date 2020/08/05 17:52
 */
@Data
public class EduCourseException extends RuntimeException {
    private Integer code;

    private String msg;

    public EduCourseException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public EduCourseException(CourseExceptionCode courseExceptionCode) {
        this.code = courseExceptionCode.getCode();
        this.msg = courseExceptionCode.getMsg();
    }
}
