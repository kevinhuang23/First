package jms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import communicate.InfoDemo.Info;
import communicate.PeopleDemo.People;
import communicate.StudentDemo.Sex;
import communicate.StudentDemo.Student;
import communicate.StudentDemo.Student.Score;

/**
 * 消息生成者
 */
public class Producer {
	public final static String QUEUE_NAME = "rabbitMQ.test";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置RabbitMQ相关信息
		factory.setHost("localhost");
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setPort(5672);
		// 创建一个新的连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		// 声明一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// for (int i = 0; i < 10; i++) {
		// String message = "Hello RabbitMQ count:"+i;
		//
		// //发送消息到队列中
		// channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		// System.out.println("Producer Send +'" + message + "'");
		// }

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
        // 构建子类
		People.Builder peopleBuilder = People.newBuilder();
		peopleBuilder.setCustId("kevinhuang");
		People people = peopleBuilder.build();
		// 访问People.address
		buidler.setExtension(people.address, 555);
		// 构建对象完成
		Student student = buidler.build();
		System.out.println(student.toString());
		// 序列化
		byte[] array = student.toByteArray();
		channel.basicPublish("", QUEUE_NAME, null, array);
		System.out.println("Producer Send +'" + student.getName() + "'");
		// 关闭通道和连接
		channel.close();
		connection.close();
	}
}
