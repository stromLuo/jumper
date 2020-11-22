package com.lt.jumper.core.provider;

import com.lt.jumper.entiey.RpcServiceProperties;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServiceProviderImpl implements ServiceProvider{

    private final Map<String, Object> cacheServiceMap;
    private final Set<String> registeredService;

    public ServiceProviderImpl() {
        this.cacheServiceMap = new ConcurrentHashMap<>();
        this.registeredService = ConcurrentHashMap.newKeySet();
    }

    /**
     * 通用服务发布
     * @param service
     * @param rpcServiceProperties
     */
    @Override
    public void publishService(Object service, RpcServiceProperties rpcServiceProperties) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            //默认第一个接口为注册服务接口
            Class<?> serviceClass = service.getClass().getInterfaces()[0];
            if(Objects.nonNull(serviceClass)){
                String rpcInterfaceName = serviceClass.getCanonicalName();
                rpcServiceProperties.builder().serviceName(rpcInterfaceName);
                //todo 注册服务-zookeeper
                this.addService(service, serviceClass, rpcServiceProperties);
            }else{
                throw new RuntimeException(service.getClass().getName() + "无可发布的服务！");
            }
        } catch (UnknownHostException e) {
            log.error("获取本地hots异常！", e);
        }
    }

    @Override
    public void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties) {
        String serviceName = rpcServiceProperties.getServiceName();
        if(registeredService.contains(serviceName)){
            log.info("已存在已注册的服务：{}", rpcServiceProperties.getServiceName());
            return;
        }
        registeredService.add(serviceName);
        cacheServiceMap.put(serviceName, service);
    }
}
