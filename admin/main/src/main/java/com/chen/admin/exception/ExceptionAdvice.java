package com.chen.admin.exception;

import com.chen.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice(basePackages = "com.chen.admin")
@Slf4j
public class ExceptionAdvice {

    /**
     * 空指针异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({NullPointerException.class})
    public Result<?> nullPointerExceptionHandler(NullPointerException e) {
        return Result.failed("空指针异常");
    }

    /**
     * 类型转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ClassCastException.class})
    public Result<?> classCastExceptionHandler(ClassCastException e) {
        return Result.failed("类型转换异常");
    }

    /**
     * IO异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IOException.class})
    public Result<?> iOExceptionHandler(IOException e) {
        return Result.failed("IO异常");
    }

    /**
     * 数字格式异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({NumberFormatException.class})
    public Result<?> numberFormatExceptionHandler(NumberFormatException e) {
        return Result.failed("数字格式异常");
    }

    /**
     * 捕获运行时异常
     * @param e：异常
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public Result<?> handleBusinessException(RuntimeException e){
        return Result.failed(e.getMessage());
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法被捕捉时选择使用
     */
    @ExceptionHandler({Exception.class})
    public Result<?> handle(Exception ex) {
        return Result.failed(ex.getMessage());
    }

}
