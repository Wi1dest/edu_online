package com.lsy.exception;

import com.lsy.common.utils.AliVodExceptionCode;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname AliVodException
 * @Description 阿里视频点播异常
 * @Date 2020/08/09 13:10
 */
@Data
public class AliVodException extends RuntimeException {
    private Integer code;

    private String msg;

    public AliVodException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AliVodException(AliVodExceptionCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
