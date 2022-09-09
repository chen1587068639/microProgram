package com.chen.admin.aspect;

import com.alibaba.fastjson.JSON;
import com.chen.common.entity.RequestEntity;
import com.chen.common.entity.RequestErrorEntity;
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

//    /**
//     * 打印请求的信息
//     * @param joinPoint:切面信息
//     */
//    @Before("requestServer()")
//    public void logBefore(JoinPoint joinPoint){
//        ServletRequestAttributes attributes = (ServletRequestAttributes)
//                RequestContextHolder.getRequestAttributes();
//        assert attributes != null;
//        HttpServletRequest request = attributes.getRequest();
//        //打印请求参数
//        log.info("IP:{},URL:{}, HTTP Method:{} ,Class Method:{}.{}",request.getRemoteAddr(),request.getRequestURL().toString(),request.getMethod(),joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//    }
//
//    @After("requestServer()")
//    public void doAfter(JoinPoint joinPoint) {
//        log.info("request: {}.{} end",joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//    }
    @Around("requestServer()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setIp(request.getRemoteHost());
            requestEntity.setUrl(request.getRequestURL().toString());
            requestEntity.setHttpMethod(request.getMethod());
            requestEntity.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName()));
            requestEntity.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
            requestEntity.setResult(result);
            requestEntity.setTimeCost(System.currentTimeMillis() - start);
            log.info("Request Success Info : {}", JSON.toJSONString(requestEntity));
        }
        return result;
    }

    /**
     *  捕获异常后规范化打印日志
     * @param joinPoint：
     * @param exception：捕获的异常
     */
    @AfterThrowing(pointcut = "requestServer()", throwing = "exception")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException exception) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            RequestErrorEntity requestErrorEntity = new RequestErrorEntity();
            requestErrorEntity.setIp(request.getRemoteAddr());
            requestErrorEntity.setUrl(request.getRequestURL().toString());
            requestErrorEntity.setHttpMethod(request.getMethod());
            requestErrorEntity.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
            requestErrorEntity.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
            requestErrorEntity.setException(exception);
            log.info("Request Error Info : {}", JSON.toJSONString(requestErrorEntity));
        }
    }

    /**
     * 获取入参
     * @param proceedingJoinPoint
     *
     * @return
     * */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();
        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
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
