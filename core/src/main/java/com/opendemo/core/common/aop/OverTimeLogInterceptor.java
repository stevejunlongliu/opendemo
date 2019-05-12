package com.opendemo.core.common.aop;

import com.opendemo.core.common.annotation.OverTimeLogAttr;
import com.opendemo.core.common.annotation.SessionAttr;
import com.opendemo.core.common.exception.UserInvalidException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashSet;
import java.util.Set;

//超时日志判断
@Aspect
@Component
public class OverTimeLogInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //Gson gson = new Gson();


    //定义拦截注解
    @Pointcut("@annotation(com.opendemo.core.common.annotation.OverTimeLogAttr)")
    public void controllerMethodPointcut() {

    }


    //拦截器实现
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        //取出注解值
        int overTimeParams = method.getAnnotation(OverTimeLogAttr.class).overTimeSeconds();


        Object reslut = null;
        long startTime = System.currentTimeMillis();
        try {
            reslut = pjp.proceed();

            long overTime = System.currentTimeMillis() - startTime;
            if (overTime > overTimeParams * 1000) {
                //打印方法名
                StringBuilder sw = new StringBuilder();
                sw.append("业务超时:" + overTime);
                sw.append(" methodName:" + method.getName());

                Object[] args = pjp.getArgs();
                int paramIndex = 0;
                sw.append(" inparams:");
                for (Parameter parameter : method.getParameters()) {
                    sw.append(parameter.getName() + ":" + args[paramIndex].toString());
                    paramIndex++;
                }

                sw.append(" out:" + reslut.toString());

                logger.info(sw.toString());//打印输入，输出，方法名
            }
        } catch (Throwable e) {

            throw e;
        }

        return reslut;

    }


}
