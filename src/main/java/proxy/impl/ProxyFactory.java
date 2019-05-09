package proxy.impl;

//JDK动态代理类：最大缺点是需要提供接口
//好处：先预设被代理对象，之后临时替换即可
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory implements InvocationHandler{
    //要拦截的对象
	private Object object;
	
	public void setObject(Object object) {
		this.object = object;
	}
    	
	//生成代理实例
	public Object getProxy() {
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
	}
	
	//代理器	,代理对象、对象方法、参数,每次调用原对象方法，就会触发此invoke，称为回调函数
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if("select".equals(method.getName())){
				Object result=method.invoke(object, args);
		        System.out.println(args[0]+"kevin");
		        System.out.println(result);
				return result;
			}
//			// TODO Auto-generated method stub
//			System.out.println("之前，事务处理");
//			Object result=method.invoke(object, args);
//			System.out.println("之后，事务处理");
//			
			return proxy;
		
	}
	

}
