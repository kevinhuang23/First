package DobboHello;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApplication{

    public static void main(String[] args) throws IOException{

        String[]springConfigLocation=new String[]{"DobboHello/consumer.xml"};

        ClassPathXmlApplicationContext  springContext =new ClassPathXmlApplicationContext(springConfigLocation);
        
        springContext.start();

        DemoService dubboTestProvider=(DemoService)springContext.getBean("demoService");

        dubboTestProvider.sayHello("hellokevin");
        System.in.read();


    }

}