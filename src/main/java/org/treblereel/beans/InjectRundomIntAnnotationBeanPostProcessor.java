package org.treblereel.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

/**Bean Definition exists, allows to setup bean before it get 
 * to container
 * BeanPostProcessors -> Chain of responsibility, step by step thru Processors
 * 
 * @author dtikhomirov
 *
 */
public class InjectRundomIntAnnotationBeanPostProcessor implements BeanPostProcessor{

	
	/**
	 * here every thing like proxy setup
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object o, String beanName) throws BeansException {
		System.out.println(getClass().getSimpleName() + " postProcessAfterInitialization");
		Field[] fields = o.getClass().getDeclaredFields();
		
		for(Field f: fields){
			System.out.println(f.getName() + " " + f.isAccessible());
			if(f.isAnnotationPresent(InjectRundomInt.class)){
			    int min = f.getAnnotation(InjectRundomInt.class).min();
			    int max = f.getAnnotation(InjectRundomInt.class).max();
			    Random r = new Random(); 
			    f.setAccessible(true);
			    ReflectionUtils.setField(f, o,(min + r.nextInt(max-min)+1) );
			}
		}
		return o;
	}
	
	// init-method
	// afterPropertySet
	// @PostConstract

	
	/**
	 * here logic to setup that bean
	 */
	@Override
	public Object postProcessBeforeInitialization(Object o, String beanName) throws BeansException {
		System.out.println(getClass().getSimpleName() + " postProcessBeforeInitialization");
		return o;
	}

}
