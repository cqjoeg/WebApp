package org.smart4j.chapter1.helper;


import org.smart4j.chapter1.annotation.Controller;
import org.smart4j.chapter1.annotation.Service;
import org.smart4j.chapter1.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }


    /**
     * 获取应用包名下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 返回有Service类
     *
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = CLASS_SET
                .stream()
                .filter(cls -> cls.isAnnotationPresent(Service.class))
                .collect(Collectors.toSet());
        return classSet;
    }

    /**
     * 返回有Controller类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = CLASS_SET
                .stream()
                .filter(cls -> cls.isAnnotationPresent(Controller.class))
                .collect(Collectors.toSet());
        return classSet;
    }

    /**
     * 返回所有的Bean
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

}

