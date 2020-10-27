package com.lt.jumper.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取单例对象的工厂类
 * Created by 罗天 on 2020/10/27
 */
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
                        instance = t.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return t.cast(instance);
    }

}
