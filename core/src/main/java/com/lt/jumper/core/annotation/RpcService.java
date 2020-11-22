package com.lt.jumper.core.annotation;

import java.lang.annotation.*;

/**
 * rpc service注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RpcService {

    /**
     * 标识服务版本号
     * @return
     */
    String version() default "";

    /**
     * 标识服务分组
     * @return
     */
    String group() default "";
}
