package proxy.impl;

import proxy.Dog;

public class CatImpl implements Dog {

	private DogImpl dogImpl =null;
	private String username =null;
	
	static String userId="kevin";
	
	public CatImpl() {}
	public CatImpl(DogImpl dogImpl,String username) {
		this.dogImpl=dogImpl;
		this.username=username;
		System.out.println("----construct-----");
	}
	
	public void sayHello() {
		System.out.println("hello ,i am cat");
		
	}
	
	public void eat() {
		dogImpl.eat();
		System.out.println("and fruit");
		System.out.println(username);
		
	}
		
	public void setDogImpl(DogImpl dogImpl) {
		this.dogImpl = dogImpl;
		System.out.println("----set-----");
	}

	public void setUsername(String username) {
		this.username = username;
	}
	


}
