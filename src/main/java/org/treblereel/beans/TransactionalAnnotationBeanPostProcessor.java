package org.treblereel.beans;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TransactionalAnnotationBeanPostProcessor implements BeanPostProcessor {
	
	// Maybe someone already did proxy, so in After we will lost class metadata
	// Because it is a good practice to do proxy's on After stage.
	// but on Before stage we still work with original objects
	private Map<String,Class<?>> classes = new HashMap<String,Class<?>>();
	
	
	@Override
	public Object postProcessBeforeInitialization(Object o, String beanName) throws BeansException {
		classes.put(beanName, o.getClass());
		System.out.println(getClass().getSimpleName() + " postProcessBeforeInitialization");
		return o;
	}

	
	
	@Override
	public Object postProcessAfterInitialization(final Object o, String beanName) throws BeansException {
		System.out.println(getClass().getSimpleName() + " postProcessAfterInitialization");
		//Class<?> beanClass =  o.getClass();
		Class<?> beanClass =  classes.get(beanName);
		if(beanClass.isAnnotationPresent(Transactional.class)){
			ClassLoader classLoader = beanClass.getClassLoader();
			return Proxy.newProxyInstance(classLoader, beanClass.getInterfaces(), new InvocationHandler() {
				@Override
				public Object invoke(Object obj, Method method, Object[] aobj) throws Throwable {
					System.out.println("Transactional begin");
					Object result = method.invoke(o, aobj);
					System.out.println("Transactional end");
					return result; // <- method return result
				}
			});
			
		}
		return o;
	}

}
