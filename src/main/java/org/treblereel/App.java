package org.treblereel;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.treblereel.beans.User;
import org.treblereel.beans.UserInterface;

public class App 
{
    public static void main( String[] args )
    {
        new App();
    }
    
    public App(){
    	
    	//BeanDefainitionReader scanning for bean, BeanDifinition contains data how beans be 
    	//after creation,BeanFactory creats beans
    	
    	//BeanPostProcessor
    	
    	
    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    	UserInterface user  = (UserInterface) ctx.getBean(UserInterface.class);  // better use autowired
    	/*
    	User user  = (User) ctx.getBean(User.class);  //lookup by className BUT  No qualifying bean of type [org.treblereel.beans.User] is defined
    	//because we changed the class to the proxy, we should use bean id
    	 */
    	user.sayHello();
    	System.out.println(new Date(ctx.getStartupDate()));
    }
}
