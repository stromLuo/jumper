package com.lt.jumper.core.annotation;

import java.lang.annotation.*;

/**
 * 扫描自定义注解，扫描服务提供端的service
 * Created by 罗天 on 2020/10/27
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcScan {
    String[] serverScanPackages();
}
