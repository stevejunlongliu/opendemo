package com.opendemo.core.common.aop;

import com.opendemo.core.common.annotation.SessionAttr;
import com.opendemo.core.common.config.CommonKeys;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class SessionInfoArgumentResolver implements HandlerMethodArgumentResolver {
    //@Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(SessionAttr.class);
    }

    //@Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        Object sessionUser = nativeWebRequest.getAttribute(CommonKeys.SessionUserKey, RequestAttributes.SCOPE_SESSION);//session取出来注入

        return sessionUser;
    }
}
