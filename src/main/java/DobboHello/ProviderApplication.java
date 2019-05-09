package DobboHello;

import java.io.IOException;


import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ProviderApplication{

    public static void main(String[] args){

        String[] springConfigLocation=new String[]{"DobboHello/provider.xml"};

        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext  springContext =new ClassPathXmlApplicationContext(springConfigLocation);

        springContext.start();

        try {

            System.in.read();

        }catch(IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

}
