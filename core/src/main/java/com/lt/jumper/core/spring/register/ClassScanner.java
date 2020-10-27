package com.lt.jumper.core.spring.register;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * 包扫描器
 * Created by 罗天 on 2020/10/27
 */
public class ClassScanner extends ClassPathBeanDefinitionScanner {

    /**
     * 设置扫描类型，扫描我们关心的类
     * @param registry
     * @param annotationType
     */
    public ClassScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annotationType) {
        super(registry);
        super.addIncludeFilter(new AnnotationTypeFilter(annotationType));
    }

}
