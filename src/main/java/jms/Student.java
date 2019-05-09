package jms;

import java.io.Serializable;
 
/**
 * 学生实体类
 * @author zhangqi
 */
public class Student implements Serializable {
 
	/**
	 *  Student类一定要实现serializable接口。
	 *  一个类只有实现了serializable才是可以序列化的，通俗的讲实现了serializable接口后我
	 *  们将可以把这个类，在网络上进行发送，或者将这个类存入到硬盘，序列化的目的就是保存一个对象。
	 */
	private static final long serialVersionUID = -245693081951194415L;
	
	private String id;
	private String name;
	
	public Student (String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return id + ", " + name;
	}
}

