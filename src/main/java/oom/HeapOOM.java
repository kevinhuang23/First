package oom;

import java.util.ArrayList;
import java.util.List;

/*VM args:-Xms20m -Xmx20m -XX:+PrintGCDetails -XX:SurvivorRatio =8
 * 
 * -Xms:堆的最小值；-Xmx：堆的最大值
 * 堆溢出，实例对象太大或太多，设定每个线程使用的空间
 * */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
//        List<OOMObject> list = new ArrayList<OOMObject>();
//        while (true) {
//            list.add(new OOMObject());
//        }
    	for(int i=0;i<30;i++) {
    	byte[] b = new byte[1 * 1024 * 1024];
    	}
    	System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");     //系统的最大空间

    	System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");   //系统的空闲空间

    	System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");   //当前可用的总空间
    }
}
