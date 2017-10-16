package com.yan.base.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by yanshuai on 2017/6/19.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionLog {
    String description() default "";
}
