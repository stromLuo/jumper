package com.lt.jumper.core.provider;

import com.lt.jumper.entiey.RpcServiceProperties;

/**
 * 发布和提供实例化service
 */

public interface ServiceProvider {

    /**
     * 发布服务
     * @param service
     * @param rpcServiceProperties
     */
    void publishService(Object service, RpcServiceProperties rpcServiceProperties);

    /**
     * 添加服务本地缓存
     * @param service
     * @param serviceClass
     * @param rpcServiceProperties
     */
    void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties);

}
