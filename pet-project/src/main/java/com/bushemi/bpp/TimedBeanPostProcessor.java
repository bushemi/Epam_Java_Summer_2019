package com.bushemi.bpp;

import com.bushemi.annotations.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TimedBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(Timed.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Annotation[] annotations = (bean.getClass()).getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Timed.class)) {
                Class<?> aClass = bean.getClass();

                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(aClass);

                includeCallbackForTimedClassBean(aClass, enhancer, bean);

                return enhancer.create();
            }
        }
        return bean;
    }

    private void includeCallbackForTimedClassBean(Class<?> aClass, Enhancer enhancer, Object bean) {
        enhancer.setCallback((MethodInterceptor) (ob, method, args, proxy) -> {
            Method[] allMethods = (aClass).getMethods();
            for (Method m : allMethods) {
                if (m.getName().equals(method.getName())) {
                    long before = System.nanoTime();
                    Object result = m.invoke(bean, args);
                    LOG.info("class = {}, method = {}, time spent in nanoseconds = {}",
                            aClass.getSimpleName(), method.getName(), (System.nanoTime() - before));
                    return result;
                }
            }
            return proxy.invokeSuper(ob, args);
        });
    }
}
