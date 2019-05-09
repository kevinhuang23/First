package Aop;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//定义切面，定义拦截后方法

public class AopTest {
	public static void main(String[] args) {
		//获得环境
		ApplicationContext context = new ClassPathXmlApplicationContext("Aop/aop.xml");
		//获得代理类，在xml文件内注册
		Waitor waitor=context.getBean("waitor", Waitor.class);
		waitor.ServeTo("huang","kiss");
		waitor.GreatTo("huang");
		
	}

}
