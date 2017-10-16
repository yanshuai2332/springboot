package com.yan.base.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by yanshuai on 2017/6/16.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    String description() default "";
}
