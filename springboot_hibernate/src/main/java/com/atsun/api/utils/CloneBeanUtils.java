package com.atsun.api.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/01/25</p>
 *
 * @author LD
 */
public class CloneBeanUtils extends BeanUtils {

    public static void copyProperties(Object source, Object target, boolean copyNull) throws BeansException {
        copyProperties(source, target, null, copyNull, (String[]) null);
    }

    public static void copyProperties(Object source, Object target, Class<?> editable, boolean copyNull, String... ignoreProperties) throws BeansException {

        if (null == source || null == target) {
            throw new IllegalArgumentException("Source class and target class can not be null");
        }

        Class<?> actualEditable = target.getClass();

        if (null != editable) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException(String.format("Target class [%s] not assignable to editable class [%s]", target.getClass().getName(), editable.getName()));
            }
            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);

        List<String> ignoreList = (null != ignoreProperties) ? Arrays.asList(ignoreProperties) : null;

        Arrays.stream(targetPds).forEach(targetPd -> {

            Method writeMethod = targetPd.getWriteMethod();
            boolean ignore = null != ignoreList && ignoreList.contains(targetPd.getName());

            if (null == writeMethod || ignore) {
                return;
            }

            PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());

            if (null == sourcePd) {
                return;
            }

            Method readMethod = sourcePd.getReadMethod();

            if (null == readMethod || !ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                return;
            }

            try {
                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                    readMethod.setAccessible(true);
                }

                Object value = readMethod.invoke(source);

                if (!copyNull && null != value) {
                    return;
                }

                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }

                writeMethod.invoke(target, value);
            } catch (Throwable ex) {
                throw new FatalBeanException(String.format("Can't copy property [%s] from source to target", targetPd.getName()), ex);
            }
        });
    }

}
