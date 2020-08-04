package com.lsy.exception;

import com.lsy.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : Lo Shu-ngan
 * @Classname GlobalExceptionHandler
 * @Description 全局异常处理
 * @Date 2020/08/02 22:38
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EduTeacherException.class)
    public Result EduTeacherException(EduTeacherException e){
        log.warn(e.getMsg());
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(e.getCode());
        result.setMessage(e.getMsg());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        log.warn(e.getMessage());
        return Result.error("发生未知异常!");
    }
}