package com.zzx.humor.exception;

import com.zzx.humor.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * humor-oauth 异常处理器
 */
@RestControllerAdvice
@Slf4j
public class HuExceptionHandler {

    @ExceptionHandler(value = HuRuntimeException.class)
    public R defaultErrorHandler(HuRuntimeException huRuntimeException){
        return R.failed(huRuntimeException.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public R defaultErrorHandler(RuntimeException e) {
        log.error(e.getMessage(),e);
        return R.failed();
    }
}
