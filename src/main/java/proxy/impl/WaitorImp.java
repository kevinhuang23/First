package proxy.impl;

import proxy.Waitor;

public class WaitorImp implements Waitor{

	

	@Override
	public void ServeTo(String name,String action) {
		System.out.println("hello"+name+action);
		
	}

	@Override
	public void GreatTo(String name) {
		System.out.println("hello"+name);
		
	}

}
