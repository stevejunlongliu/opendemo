package com.opendemo.core.common.annotation;


import java.lang.annotation.*;

//自动注入session标识
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionAttr {


}
