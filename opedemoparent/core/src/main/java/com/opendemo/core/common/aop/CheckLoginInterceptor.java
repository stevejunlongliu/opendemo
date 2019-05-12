package com.opendemo.core.common.aop;

import com.opendemo.core.common.annotation.SessionAttr;
import com.opendemo.core.common.exception.UserInvalidException;
import com.opendemo.core.common.model.UserInfo;
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

@Aspect
@Component
public class CheckLoginInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //Gson gson = new Gson();


    //定义拦截注解
    @Pointcut("@annotation(com.opendemo.core.common.annotation.CheckLoginAttr)")
    public void controllerMethodPointcut() {

    }


    //拦截器实现
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodNmae = method.getName();
        Set<Object> allParams = new LinkedHashSet<Object>();
        Object[] args = pjp.getArgs();
        Class<?>[] ptypes = method.getParameterTypes();

        //method.getParameters()[0].getAnnotations()[0].annotationType().equals(SessionAttr.class)
        Parameter[] params = method.getParameters();
        int paramIndex = 0;

        //根据注解的方式去检查，就不需要依赖应用具体的业务model，避免业务代码污染core
        for (Parameter param : params) {//遍历所有参数检查是否有session 注解

            for (Annotation annotation : param.getAnnotations()) {
                if (annotation.annotationType().equals(SessionAttr.class)) {
                    if (args[paramIndex] == null) {
                        throw new UserInvalidException("用户信息失效");
                    }

                }
            }

        }


        //原方法，这种方法会依赖业务UserInfo类，对core的边界有跨界
        /*
        for (Class<?> ptype : ptypes) {
            if (ptype.isAnnotationPresent(SessionAttr.class)) {
                //判断是否有session注解
                Object loginUser = args[paramIndex];
                logger.info(loginUser.toString());
            }


            //判断对应userinfo是否已经注入
            if (ptype.isAssignableFrom(UserInfo.class)) {
                Object loginUser = args[paramIndex];
                if (loginUser == null) {//未登陆则抛异常
                    //todo 这里抛出异常，会导致报错没有业务的堆栈信息
                    throw new UserInvalidException("用户信息失效");
                }
            }
            paramIndex++;//自增遍历参数
        }
*/

        Object reslut = null;
        try {
            reslut = pjp.proceed();
        } catch (Throwable e) {
            throw e;
        }

        return reslut;

    }
}
