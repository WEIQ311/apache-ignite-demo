package org.apache.ignite.aspect;


import cn.hutool.core.date.DateUtil;
import org.apache.ignite.enums.GlobalEnum;
import org.apache.ignite.util.GlobalUtils;
import org.apache.ignite.util.ResultUtil;
import org.apache.ignite.vo.ResultEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * AOP处理
 *
 * @author weiQiang
 * @date 2020-03-09
 */
@Slf4j
@Aspect
@Component
@Order(-1)
public class CommonAspect {

    /**
     * 请求进入时间
     */
    public static ThreadLocal<Long> BEGIN_TIME = new ThreadLocal<>();
    /**
     * 应用系统名称
     */
    @Value("${spring.application.name:quick-frame-ai}")
    private String applicationName;

    /**
     * 返回时拦截
     *
     * @param pjp 返回结果
     */
    @Around("doController()")
    public Object doAfterReturning(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        long beginTime = System.currentTimeMillis();
        BEGIN_TIME.set(beginTime);
        String now = DateUtil.now();
        String requestUri = request.getRequestURI();
        //获取请求参数
        String requestParam = getParams(pjp.getArgs());
        log.debug("系统:{},接口:{},请求参数:{}", applicationName, requestUri, requestParam);
        String requestType = request.getMethod().toUpperCase();
        String browserName = GlobalUtils.getBrowserName(request);
        String browserVersion = GlobalUtils.getBrowserVersion(request);
        String osName = GlobalUtils.getOsName(request);
        Object result = null;
        boolean requestFlag = true;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            requestFlag = false;
            final String message = throwable.getMessage();
            log.error("接口:{},发生错误:{}", requestUri, message);
            if (StringUtils.isNotBlank(message)) {
                result = ResultUtil.error(message);
            } else {
                result = ResultUtil.error(GlobalEnum.QueryError);
            }
        } finally {
            BEGIN_TIME.remove();
        }
        long requestTime = System.currentTimeMillis() - beginTime;
        log.info("系统:{},接口:{},请求结束,耗时:{}ms,返回状态:{}", applicationName, requestUri, requestTime, requestFlag);
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        final String returnType = method.getReturnType().getTypeName();
        //ResponseEntity 返回时
        if (result instanceof ResultEntity) {
            if (returnType.contains(ResponseEntity.class.getName())) {
                return ResponseEntity.ok();
            }
            ((ResultEntity) result).setTotalTime(requestTime);
            return result;
        }
        log.info("返回执行名称:{}", returnType);
        return result;
    }

    /**
     * 获取请求参数
     *
     * @param arguments 请求参数
     * @return String
     */
    private String getParams(Object[] arguments) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Object object : arguments) {
                if (Objects.nonNull(object)) {
                    stringBuilder.append(object);
                }
            }
        } catch (Exception e) {
            log.error("获取请求参数发生错误:{}", e.getMessage());
        }
        return stringBuilder.toString();
    }


    /**
     * 切点
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void doController() {
    }
}
