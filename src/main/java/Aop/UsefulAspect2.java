package Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
//@Aspect
public class UsefulAspect2 {
	//任意参数的serveTo方法
//	@Pointcut("execution(* Aop.WaitorImp.*(..))")
//	private void anyMethod() {}	
//	@Before("anyMethod()")     
    public void before() {
		System.out.println("hello,it's really before:");
	}
    
//	@AfterReturning(value="execution(* Aop.Waitor.*(..))",returning = "returnVal")
	public void AfterGreet(JoinPoint joinPoint,Object returnVal){
        System.out.println("方法结束后通知...."+returnVal);
    }
	

	public void after() {
		System.out.println("最终通知....");
	}
//    @Before("anyMethod()&&args(name,action)")     
//    public void before(String name,String action) {
//		System.out.println("hello,it's really before:"+name+action);
//	}
//	@AfterThrowing(pointcut="anyMethod()",throwing="e")
//	public void throwError(Exception e) {
//		System.out.println(e);
//	}
	

//	@Around("execution(* GreatTo(..))")
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.println("hello,before");
//		Object obj= (Object) joinPoint.proceed();
//		System.out.println("hello,after");
//		return obj;
//	}
//	
//	
//	


}
