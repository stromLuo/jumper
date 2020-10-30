package com.lt.jumper.factory;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取单例对象的工厂类
 * Created by 罗天 on 2020/10/27
 */
@Slf4j
public class SingletonFactory {
    private static final Map<String, Object> OBJECT_MAP = new HashMap<>();

    private SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> t){
        String key = t.getCanonicalName();
        Object instance = null;
        if(instance == null){
            synchronized (SingletonFactory.class){
                instance = OBJECT_MAP.get(key);
                if(instance == null){
                    try {
                        instance = t.getDeclaredConstructor().newInstance();
                        OBJECT_MAP.put(key, instance);
                    } catch (IllegalAccessException | InstantiationException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    } catch (NoSuchMethodException | InvocationTargetException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }
        return t.cast(instance);
    }
}
