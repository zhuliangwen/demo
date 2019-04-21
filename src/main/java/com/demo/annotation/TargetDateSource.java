package com.demo.annotation;

import com.demo.enums.DataSourceKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //作用再方法上
@Retention(RetentionPolicy.RUNTIME) //运行时有效
public @interface TargetDateSource {
    DataSourceKey dataSourceKey() default DataSourceKey.DB_MASTER;
}
