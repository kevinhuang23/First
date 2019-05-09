package Aop;

import org.springframework.stereotype.Component;

@Component(value="waitor")
public class WaitorImp implements Waitor{
	String name;
	public WaitorImp(String name) {
		this.name=name;
	}
	public WaitorImp() {};

	@MyAnnotation
	@Override
	public void ServeTo(String name,String action) {
		System.out.println("hello"+name+action);
//		throw new IllegalArgumentException();
		
		
	}

	@Override
	public void GreatTo(String name) {
		System.out.println("GreetTo"+name);
		
	}

}
