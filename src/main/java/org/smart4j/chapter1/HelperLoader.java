package org.smart4j.chapter1;

import org.smart4j.chapter1.helper.BeanHelper;
import org.smart4j.chapter1.helper.ClassHelper;
import org.smart4j.chapter1.helper.ControllerHelper;
import org.smart4j.chapter1.helper.IocHelper;
import org.smart4j.chapter1.util.ClassUtil;

public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                ControllerHelper.class,
                IocHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), false);
        }
    }


}
