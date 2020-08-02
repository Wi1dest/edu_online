package com.lsy.common_utils;

import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname Result
 * @Description 通用返回对象
 * @Date 2020/08/02 14:39
 */
@Data
public class Result {
    private Boolean success;

    private Integer code;

    private String message;

    private Object data;

    /**
     * 成功操作
     * @param data 传到前端的数据
     */
    public Result(Object data){
        success = true;
        code = ResultCodeEnums.SUCCESS.getCode();
        message = ResultCodeEnums.ERROR.getMsg();
        this.data = data;
    }

    /**
     * 成功操作(静态)
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnums.SUCCESS.getCode());
        result.setMessage(ResultCodeEnums.SUCCESS.getMsg());
        return result;
    }

    /**
     * 失败操作(静态)
     * @return
     */
    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnums.ERROR.getCode());
        result.setMessage(ResultCodeEnums.ERROR.getMsg());
        return result;
    }
}
