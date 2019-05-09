package Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

//定义切面
@Aspect
// 声明组件
@Component
public class UsefulAspect {
	// 任意参数的serveTo方法
	// @Pointcut("execution(* Aop.WaitorImp.*(..)) &&
	// @annotation(Aop.MyAnnotation)")
	// private void anyMethod() {}
	// @Before("anyMethod()")
	// public void before() {
	// System.out.println("hello,it's really before:");
	// }

	// @Before("anyMethod()&&args(name,action)")
	// public void before(String name,String action) {
	// System.out.println("hello,it's really before:"+name+action);
	// }
	// @AfterThrowing(pointcut="anyMethod()",throwing="e")
	// public void throwError(Exception e) {
	// System.out.println(e);
	// }

	@Around("execution(* GreatTo(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("hello,before");
		Object obj = (Object) joinPoint.proceed();
		System.out.println("hello,after");
		Object[] obj1 = joinPoint.getArgs();
		for (Object ob : obj1) {
			System.out.println((String) ob);
			System.out.println(ob.getClass().getName());
		}
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Class[] types = ms.getParameterTypes();
		String[] names = ms.getParameterNames();
		for (String s : names) {
			System.out.println(s);
		}
		for (Class s : types) {
			System.out.println(s);
		}
		return obj;
	}
	//
	//
	//
	// @AfterReturning(value="execution(* Aop.Waitor.*(..))",returning =
	// "returnVal")
	// public void AfterGreet(JoinPoint joinPoint,Object returnVal){
	// System.out.println("后置通知...."+returnVal);
	// }
	//
	// @After("execution(public * *(..))")
	// public void after() {
	// System.out.println("最终通知....");
	// }

}
