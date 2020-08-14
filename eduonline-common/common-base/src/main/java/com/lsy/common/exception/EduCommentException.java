package com.lsy.common.exception;

import com.lsy.common.enums.CommentExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduCommentException
 * @Description 评论异常
 * @Date 2020/08/09 13:10
 */
@Data
public class EduCommentException extends RuntimeException {
    private Integer code;

    private String msg;

    public EduCommentException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public EduCommentException(CommentExceptionCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
