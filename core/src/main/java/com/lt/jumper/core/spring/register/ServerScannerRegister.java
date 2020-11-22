package com.lt.jumper.core.spring.register;

import com.lt.jumper.core.annotation.RpcScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * 扫描注册service
 * Created by 罗天 on 2020/10/27
 */
@Slf4j
public class ServerScannerRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private static final String INNER_BEAN_BASE_BACKAGES = "com.lt.jumper.core.spring";
    private static final String BASE_PACKAGE_ATTRIBUTE_NAME = "serverScanPackages";
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes rpcScanAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RpcScan.class.getName()));

        if(rpcScanAttributes != null){
            String[] rpcPackages = rpcScanAttributes.getStringArray(BASE_PACKAGE_ATTRIBUTE_NAME);
            if(rpcPackages != null && rpcPackages.length > 0){
                // Scan the RpcService annotation
                ClassScanner rpcScanner = new ClassScanner(registry, RpcScan.class);
                ClassScanner springBeanScanner = new ClassScanner(registry, Component.class);
                if(resourceLoader != null){
                    rpcScanner.setResourceLoader(resourceLoader);
                    springBeanScanner.setResourceLoader(resourceLoader);
                }
                int rpcCount = rpcScanner.scan(rpcPackages);
                log.info("jumper------当前注册的服务数为：{}", rpcCount);
                springBeanScanner.scan(INNER_BEAN_BASE_BACKAGES);
            }else{
                //未正确设置包名
                throw new RuntimeException("please check annotation {RpcScan} is it set correctly");
            }

        }
    }
}
