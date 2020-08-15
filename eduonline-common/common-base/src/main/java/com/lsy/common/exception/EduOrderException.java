package com.lsy.common.exception;

import com.lsy.common.enums.OrderExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduOrderException
 * @Description 订单异常
 * @Date 2020/08/09 13:10
 */
@Data
public class EduOrderException extends RuntimeException {
    private Integer code;

    private String msg;

    public EduOrderException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public EduOrderException(OrderExceptionCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
