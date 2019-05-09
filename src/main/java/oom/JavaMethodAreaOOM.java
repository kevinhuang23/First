package oom;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/*VM args: -XX:PermSize=5m -XX:MaxPermSize=5m
 * 
 * 方法区溢出
 * */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {   	
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    //return method.invoke(obj, args);
                    return proxy.invokeSuper(obj, args);
                }
            });
            OOMObject object = (OOMObject) enhancer.create();
        }

    }

    static class OOMObject {
    }
}
