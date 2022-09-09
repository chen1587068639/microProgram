package com.chen.admin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    /**
     * 执行controller包中定义的任何方法
     */
    @Pointcut("execution( * com.chen.admin.controller..*(..))")
    public void requestServer() {
    }

    /**
     * 打印请求的信息
     * @param joinPoint:切面信息
     */
    @Before("requestServer()")
    public void logBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //打印请求参数
        log.info("IP:{},URL:{}, HTTP Method:{} ,Class Method:{}.{}",request.getRemoteAddr(),request.getRequestURL().toString(),request.getMethod(),joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @Around("requestServer()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //打印请求参数，返回结果，耗时
        log.info("request params : {}", getRequestParams(proceedingJoinPoint));
        log.info("request result : {}", result);
        log.info("request expend time : {} ms", System.currentTimeMillis() - start);
        return result;
    }

    @After("requestServer()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("request: {}.{} end",joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    /**
     * 提取请求参数
     * @param proceedingJoinPoint
     * @return
     */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();
        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }
}
