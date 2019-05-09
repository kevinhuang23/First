package proxy.impl;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.MethodBeforeAdvice;


@Aspect
public class BeforeGreetingAdvice implements MethodBeforeAdvice{


	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println(arg0.getName());
				
	}
	@AfterReturning(value="execution(* com.txn.Waitor.*(..))",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }


}
