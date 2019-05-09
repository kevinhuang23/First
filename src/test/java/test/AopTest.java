package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proxy.UserService;
import proxy.Waitor;
import proxy.impl.UserServiceImpl;


public class AopTest {

	public static void main(String[] args) {
		//获得环境
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
		//获得代理类，在xml文件内注册
		//JDK动态代理
		Waitor waitor=context.getBean("waitorProxy", Waitor.class);
		waitor.ServeTo("huang","kiss");
		waitor.GreatTo("huang");
		
		//CGLIB动态代理
		UserService us=(UserService)context.getBean("userProxy", UserServiceImpl.class);
		us.delete();
		us.update();
	}

}
