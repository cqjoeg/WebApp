package org.smart4j.chapter1.helper;

import org.smart4j.chapter1.Bean.Handler;
import org.smart4j.chapter1.Bean.Request;
import org.smart4j.chapter1.annotation.Action;
import org.smart4j.chapter1.util.ArrayUtil;
import org.smart4j.chapter1.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {

            controllerClassSet.stream().forEach(cc -> {
                Method[] methods = cc.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    //遍历方法
                    Arrays.stream(methods).forEach(method -> {

                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            //验证URL 映射规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(cc, method);
                                    //初始化 Action Map
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    });
                }
            });
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
