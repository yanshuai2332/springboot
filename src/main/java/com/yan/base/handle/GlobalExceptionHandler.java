package com.yan.base.handle;

import com.yan.base.entity.Result;
import com.yan.base.util.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author yanshuai
 * @date 2017/8/4
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handle(Exception e){
        return ResultUtil.error(100,e.getMessage());
    }
}
