package com.lt.jumper.core.spring.register.processor;

import com.lt.jumper.core.annotation.RpcService;
import com.lt.jumper.core.provider.ServiceProvider;
import com.lt.jumper.core.provider.ServiceProviderImpl;
import com.lt.jumper.entiey.RpcServiceProperties;
import com.lt.jumper.factory.SingletonFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean初始化时，发布服务
 * Created by 罗天 on 2020/10/27
 */
@Slf4j
@Component
public class PublishServicePostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;

    public PublishServicePostProcessor() {
        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //扫描RpcService注解，发布服务
        if(bean.getClass().isAnnotationPresent(RpcService.class)){
            log.info("服务发布：{}", bean.getClass().getName());
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group(rpcService.group()).version(rpcService.version()).build();
            serviceProvider.publishService(bean, rpcServiceProperties);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
