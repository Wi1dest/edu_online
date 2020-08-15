package com.lsy.common.enums;

import lombok.Getter;

/**
 * @Author : Lo Shu-ngan
 * @Classname CommentExceptionCode
 * @Description 订单异常枚举
 * @Date 2020/08/05 17:53
 */
@Getter
public enum OrderExceptionCode {
    CREAT_PAY_QRCODE_ERROR(46000,"生成支付二维码失败!"),
    CHECK_PAY_STATUS_ERROR(46001,"查询支付状态失败!")

    ;
    private Integer code;

    private String msg;

    OrderExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
