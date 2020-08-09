package com.lsy.common.utils;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname CourseExceptionCode
 * @Description 课程异常枚举
 * @Date 2020/08/05 17:53
 */
@Getter
public enum AliVodExceptionCode {
    DELETE_VOD_ERROR(43000,"视频删除失败!"),
    UPLOAD_VOD_ERROR(42001,"视频上传失败!")
    ;
    private Integer code;

    private String msg;

    AliVodExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
