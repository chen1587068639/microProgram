package com.chen.admin.exception;

import com.chen.common.entity.Result;
import com.chen.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
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
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        if (StringUtils.hasText(msg)) {
            return Result.failed(msg);
        }
        return Result.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<?> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("@PathVariable/@RequestParam param 校验失败");
        if (StringUtils.hasText(ex.getMessage())) {
            return Result.failed(ex.getMessage());
        }
        return Result.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法被捕捉时选择使用
     */
    @ExceptionHandler({Exception.class})
    public Result<?> handle(Exception ex) {
        return Result.failed(ex.getMessage());
    }

}
