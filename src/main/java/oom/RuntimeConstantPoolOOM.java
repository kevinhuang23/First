package oom;

import java.util.ArrayList;
import java.util.List;

/*VM rags:-XX:PermSize=5m -XX:MaxPermSize=5m
 * 方法区溢出，常量池太满
 * 
 * */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());//将String对象加入常量池
        }
    }
}