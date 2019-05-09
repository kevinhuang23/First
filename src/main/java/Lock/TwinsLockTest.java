package Lock;



import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

import org.junit.Test;

/**
 * 在测试用例中，定义了工作者
 * 线程Worker，该线程在执行过程中获取锁，当获取锁之后使当前线程睡眠1秒（并不释放锁），
 * 随后打印当前线程名称，最后再次睡眠1秒并释放锁
 * Created by tianjun on 2017/2/28.
 */
public class TwinsLockTest {

    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        final HashMap<String,String> hp=new HashMap<>();
        final Hashtable<String,String> ht=new Hashtable<>();
        
        for (int i=0;i<10;i++){
        new Thread(new Runnable() {
            public void run(){
//                while (true){
                    lock.lock();
                    try{
                        try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//                        System.out.println(Thread.currentThread().getName());
//                        System.out.println(UUID.randomUUID().toString());
                        hp.put(UUID.randomUUID().toString(), Thread.currentThread().getName());
                        ht.put(UUID.randomUUID().toString(), Thread.currentThread().getName());
//                        try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
                    }finally {
                        lock.unlock();
                    }
                }
            }
        ).start();
        //启动10个线程
//        for (int i=0;i<10;i++){
//            Worker w = new Worker();
//            w.setDaemon(true);
//            w.start();
//        }
        
       
    }
      //需要时间让HashMap插入完成，否则取到的值为之前的
      //每隔1秒换行
        for(int k =0 ;k<2;k++){
        	    try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            System.out.println("------");
        }
        System.out.println("-----------------------------");
        System.out.println(hp.size());
        Iterator<String> iter=hp.keySet().iterator();
        while(iter.hasNext()) {  
        	String s=iter.next();
        System.out.println(hp.get(s));
        }
        System.out.println(hp.size());
        System.out.println("-----------------------------");
        for(Map.Entry<String,String > e:hp.entrySet()) {
          	System.out.println(e.getValue());
        }


}
}

