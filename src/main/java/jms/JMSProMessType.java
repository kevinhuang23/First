package jms;


import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
/**
 * 消息生产者
 * @author zhangqi
 */
public class JMSProMessType {
 
	// 默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
 
    public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer messageProducer;
        
        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSProMessType.USERNAME, JMSProMessType.PASSWORD, JMSProMessType.BROKEURL);
 
        try {
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 创建一个名称为HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            // 创建消息生产者
            messageProducer = session.createProducer(destination);
            
            // 发送消息
            sendStreamMessage(session, messageProducer);
 
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
 
    public static void sendObjectMessage(Session session,MessageProducer messageProducer) throws Exception{
        
        // 创建一个对象
      	Student stu = new Student("201701", "lilei");
        ObjectMessage message = session.createObjectMessage(stu);
        
        // 通过消息生产者发出消息
        messageProducer.send(message);
        System.out.println("发送消息： " + stu.toString());
    }
    
    public static void sendMapMessage(Session session,MessageProducer messageProducer) throws Exception{
        
    	// 创建map消息对象
        MapMessage message = session.createMapMessage();
        message.setString("name", "zhangqi");
        message.setString("company", "Pingan");
        
        // 通过消息生产者发出消息
        messageProducer.send(message);
    }
    
    public static void sendBytesMessage(Session session,MessageProducer messageProducer) throws Exception{
        
    	// 创建map消息对象
        BytesMessage message = session.createBytesMessage();
        String str = "This is a bytes message";
        message.writeBytes(str.getBytes());
        
        // 通过消息生产者发出消息
        messageProducer.send(message);
    }
    
    public static void sendStreamMessage(Session session,MessageProducer messageProducer) throws Exception{
        
    	// 创建map消息对象
    	StreamMessage message = session.createStreamMessage();
        String str = "This is a stream message";
        message.writeString(str);
        message.writeInt(2017);
        
        // 通过消息生产者发出消息
        messageProducer.send(message);
    }
}

