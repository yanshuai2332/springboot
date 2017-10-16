package com.yan.base.aop.aspect;


import com.yan.base.aop.annotation.ExceptionLog;
import com.yan.base.aop.annotation.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by yanshuai on 2017/6/16.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Value("${file.log}")
    private String basePath;

    @Pointcut("@annotation(operationLog)")
    public void operationAspect(OperationLog operationLog) {
    }

    @Pointcut("@annotation(exceptionLog)")
    public void exceptionAspect(ExceptionLog exceptionLog) {
    }

    @AfterReturning(value = "operationAspect(operationLog)", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue, OperationLog operationLog) throws FileNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.
                getRequestAttributes()).getRequest();
        writeOperationLog(returnValue, joinPoint, request, operationLog.description());
    }

    @AfterThrowing(value = "exceptionAspect(exceptionLog)", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex, ExceptionLog exceptionLog) throws FileNotFoundException {
        //写入异常日志
        writeErrorLog(joinPoint, ex, exceptionLog.description());
    }

    public void writeErrorLog(JoinPoint joinPoint, Exception ex, String description) throws FileNotFoundException {
        System.out.println("");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.
                getRequestAttributes()).getRequest();
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestURL.append("?").append(queryString);
        }
        String action = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();

        //日志具体参数
        StringBuilder sb = new StringBuilder();
        sb.append("---" + LocalDateTime.now() + "---");
        sb.append("客户端ip:"+request.getRemoteAddr()+"---");
        sb.append("远程请求URL[" + requestURL + "]---");
        sb.append("接口方法：[" + action + "." + method + "]---");
        sb.append("接口描述:" + description + "---");
        sb.append("详细错误信息：" + ex );
        logger.error(sb.toString());

    }

    public void writeOperationLog(Object returnValue, JoinPoint joinPoint, HttpServletRequest request, String description) throws FileNotFoundException {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestURL.append("?").append(queryString);
        }
        String action = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();
        //日志具体参数
        StringBuilder sb = new StringBuilder();
        sb.append("---" + LocalDateTime.now() + "---");
        sb.append("客户端ip:"+request.getRemoteAddr()+"---");
        sb.append("远程请求URL[" + requestURL + "]---");
        sb.append("接口方法：[" + action + "." + method + "]---");
        sb.append("接口描述:" + description + "---");
        sb.append("接口参数为" + Arrays.toString(joinPoint.getArgs()) + "---");
        sb.append("返回对象为：" + returnValue );
        logger.info(sb.toString());

    }


}
