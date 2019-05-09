package communicate;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.InvalidProtocolBufferException;

import communicate.InfoDemo.Info;
import communicate.PeopleDemo.People;
import communicate.StudentDemo.Sex;
import communicate.StudentDemo.Student;
import communicate.StudentDemo.Student.Score;

public class Client {

	public static void main(String[] args) {
		// 构建对象
		Student.Builder buidler = Student.newBuilder();
		buidler.setName("Frank");
		buidler.setNumber(123456);
		buidler.setHobby("music");
		buidler.setSkill("computer program");
		buidler.setSex(Sex.MALE);
		buidler.addArray(100);
		buidler.addArray(200);
		buidler.addArray(300);

		// 声明外部类
		Info.Builder infoBuilder = Info.newBuilder();
		infoBuilder.setQq(1111111);
		infoBuilder.setWeixin(222222);
		buidler.setInfo(infoBuilder);

		// 构建内部类
		Score.Builder scoreBuilder = Score.newBuilder();
		scoreBuilder.setChinese(100.1);
		scoreBuilder.setHistory(88.6);
		buidler.setScore(scoreBuilder);

		People.Builder peopleBuilder = People.newBuilder();
		peopleBuilder.setCustId("kevinhuang");
		People people = peopleBuilder.build();
		// 访问People.address
		buidler.setExtension(people.address, 555);
		// 构建
		Student student = buidler.build();
		System.out.println(student.toString());
		// 序列化
		byte[] array = student.toByteArray();
		
		

		try {
			// 反序列化
			// 注册address字段
			ExtensionRegistry registry = ExtensionRegistry.newInstance();
			registry.add(People.address);
			Student student1 = Student.parseFrom(array, registry);
			System.out.println(student1.toString());
			System.out.println(student1.getSex().toString());
			System.out.println(student1.getArray(1));

		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}

}
