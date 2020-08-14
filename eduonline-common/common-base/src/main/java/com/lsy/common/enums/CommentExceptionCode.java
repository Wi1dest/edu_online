package com.lsy.common.enums;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname CommentExceptionCode
 * @Description 课程异常枚举
 * @Date 2020/08/05 17:53
 */
@Getter
public enum CommentExceptionCode {
    COMMENT_UPDATE_ERROR(45000,"您无权修改此评论!"),
    COMMENT_DELETE_ERROR(45001,"您无权删除此评论!"),
    COMMENT_NEED_LOGIN(45002,"请先登录账户!!")
    ;
    private Integer code;

    private String msg;

    CommentExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
