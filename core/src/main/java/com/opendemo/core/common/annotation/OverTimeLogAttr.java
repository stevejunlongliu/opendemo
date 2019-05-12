package com.opendemo.core.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OverTimeLogAttr {
    int overTimeSeconds() default 1;
}
