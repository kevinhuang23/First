package cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component  //import org.springframework.stereotype.Component;    
public class MyTask {    
      @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次       
      public void myTest(){    
            System.out.println("进入测试");    
      }    
}
