package proxy.impl;

//CGlib动态代理类:无需提供接口
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactoryCg implements MethodInterceptor{
    //要拦截的对象
	private Object object;
	
	public void setObject(Object object) {
		this.object = object;
	}
	public Object getInstance(Object object) {
		this.object = object;
		Enhancer enhancer=new Enhancer();
		//CGLIB根据字节码生成被代理类的子类
		enhancer.setSuperclass(this.object.getClass());
		//回调函数
		enhancer.setCallback(this);
		//创建代理对象
		return enhancer.create();		
	}
	//每次调用原方法都会回调该方法，需要重写
	@Override
	public Object intercept(Object obj, Method method, Object[] args,MethodProxy proxy) throws Throwable {
		System.out.println("之前，事务处理le");
		Object result=proxy.invokeSuper(obj, args);
		System.out.println("之后，事务处理le");
		return result;
	}
	

}
