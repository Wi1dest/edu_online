package com.lsy.common.exception;

import com.lsy.common.enums.UcenterExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname UcenterException
 * @Description 用户异常类
 * @Date 2020/08/05 17:52
 */
@Data
public class UcenterException extends RuntimeException {
    private Integer code;

    private String msg;

    public UcenterException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UcenterException(UcenterExceptionCode ucenterExceptionCode) {
        this.code = ucenterExceptionCode.getCode();
        this.msg = ucenterExceptionCode.getMsg();
    }
}
