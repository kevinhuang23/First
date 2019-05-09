package proxy.impl;


public class Initial {
	static {
		System.out.println("here is initial's initialization");
	}
	static final int userNo = 5;
	String userName="kevin";
	private transient int password=12345;
	private CatImpl instance=new CatImpl();
	

	public Initial() {
		this.userName="kevinhuang";
	}
	
	
	private Initial(String userName,int password) {
		this.userName= userName;
		this.password=password;
	}
	
	public int getPassword() {
		return password;
	}


	public void setPassword(int password) {
		this.password = password;
	}
	public int swap(int a,int b) {
		return a+b;
	}
}
