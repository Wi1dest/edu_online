package com.lsy.common.exception;

import com.lsy.common.utils.ExcelExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname ExcelException
 * @Description excel异常
 * @Date 2020/08/04 23:27
 */
@Data
public class ExcelException extends RuntimeException {
    private Integer code;

    private String msg;

    public ExcelException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ExcelException(ExcelExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.msg = exceptionCode.getMsg();
    }
}
