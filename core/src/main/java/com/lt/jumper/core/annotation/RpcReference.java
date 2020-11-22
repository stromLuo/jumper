package com.lt.jumper.core.annotation;

import java.lang.annotation.*;

/**
 * rpc reference注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface RpcReference {

    /**@Inherited
     * 标识服务版本号
     * @return
     */
    String version() default "";

    /**
     * 标识服务分组，用于区分多个服务实现
     * @return
     */
    String group() default "";
}
