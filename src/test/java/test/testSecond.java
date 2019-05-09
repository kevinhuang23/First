package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proxy.Dog;


public class testSecond {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//获得环境
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//获得Dog类，在xml文件内注册
//		Dog ts=context.getBean("heihei1", Dog.class);
//		Dog ts=(Dog)context.getBean("heihei1");
		Dog ts=(Dog)context.getBean("heihei");
		ts.sayHello();
		ts.eat();
		   
	}

}
