package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import proxy.impl.DogImpl;



//对于Junit的支持

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})

public class testFirst {
  @Autowired
  private DogImpl dog = null;
  @Test
  public void testSayHello() {
	  dog.sayHello();
	  dog.eat();
	 
  }

   

  
	
   
 
	
}
