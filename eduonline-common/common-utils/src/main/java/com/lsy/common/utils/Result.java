package com.lsy.common.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Lo Shu-ngan
 * @Classname Result
 * @Description 通用返回对象
 * @Date 2020/08/02 14:39
 */
@Data
@NoArgsConstructor
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
        message = ResultCodeEnums.SUCCESS.getMsg();
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
     * 成功操作(静态)
     * @return
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnums.SUCCESS.getCode());
        result.setMessage(ResultCodeEnums.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 成功操作(静态+状态码)
     * @return
     */
    public static Result success(Integer code,Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(ResultCodeEnums.SUCCESS.getMsg());
        result.setData(data);
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

    /**
     * 失败操作(静态)
     * @return
     */
    public static Result error(Object data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnums.ERROR.getCode());
        result.setMessage(ResultCodeEnums.ERROR.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 失败操作(静态+状态码)
     * @return
     */
    public static Result error(Integer code,Object data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(ResultCodeEnums.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }
}
