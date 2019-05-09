package proxy.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import proxy.Dog;
import proxy.UserService;



public class TestReflect {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//反射：解析字节码文件，得到类的引用，进一步动态调用类的构造方法，构造对象		
//以下方法获得引用：
//	   Class.forname  	     
	   System.out.println("-----------");	   
	   Class<?> s1=Class.forName("proxy.impl.Initial");		   	   
	   System.out.println("-----------");
//	   classname.class
	   Class<?> s2=Initial.class;
	   System.out.println("-----------");
//	   Object.getClass
	   Initial ini=new Initial();
	   Class<?> s3=ini.getClass();
//     调用构造方法(无参）
		Initial ss2=(Initial) s2.newInstance();
		System.out.println(ss2.userName);	  
//     调用构造方法(有参数）	   
		Constructor<?> ct=s3.getDeclaredConstructor(String.class,int.class);
		//由于构造方法是private，所以要特别设置权限
		ct.setAccessible(true);
		Initial ss3=(Initial)ct.newInstance("kevinhuang23",12345);
		System.out.println(ss3.userName);		
       //调用成员变量
	   Field fields[] = s2.getDeclaredFields();
       for (Field f:fields) {
           System.out.println("f:"+f);
       }
       //获取具体的成员变量，并修改，赋值使用 
       Field field =s2.getDeclaredField("password");
       field.setAccessible(true);
       field.set(ss2, 111);
       System.out.println(ss2.getPassword());
       
       //获取具体的成员变量，直接使用静态变量
       Field field1 =s2.getDeclaredField("userNo");
       System.out.println(field1.get(s2));
       
     //获取具体的成员变量，对象类型
       Field field2 =s2.getDeclaredField("instance");
       field2.setAccessible(true);
       CatImpl ss4=(CatImpl)field2.get(ss2);
       System.out.println(ss4.userId);
//调用方法
       Method method =s3.getDeclaredMethod("swap", int.class,int.class);
       System.out.println(method.invoke(ss3, 1,2));
       Method[] methods =s3.getMethods();
       for (Method m:methods){
           System.out.println("m::"+m);
       }
//反射的使用
       UserService us=new UserServiceImpl();
       us.delete();
       us.update();
       System.out.println("-----------");
       //JDK 动态代理的原理
       //得到代理类
       ProxyFactory pf=new ProxyFactory();
       //输入被代理对象
       pf.setObject(us);
       //得到代理对象接口，调用相关接口方法
       UserService us1=(UserService)pf.getProxy();
       //以下方法会出发invoke方法
       us1.delete();
       us1.select("huangcheng");
       System.out.println("-----------");
       
       
       
       //CGlIB代理原理
       //得到代理对象实体，调用相关方法,AOP
       ProxyFactoryCg pg=new ProxyFactoryCg();
       //强制类型转换的两种方式：Isinstance VS instanceof
//     if(pg.getInstance(us) instanceof UserServiceImpl) {
    	   if(UserService.class.isInstance(pg.getInstance(us))) {
    	   System.out.println("强制类型转换了");
       UserServiceImpl us2=(UserServiceImpl)pg.getInstance(us);
       us2.insert("huangcheng");
       }
       
       
       

       
	}

}
