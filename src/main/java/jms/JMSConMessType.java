package jms;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
 
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
/**
 * 消息消费者
 * @author zhangqi
 */
public class JMSConMessType {
 
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
 
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接
 
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
 
        MessageConsumer messageConsumer; // 消息的消费者
 
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSConMessType.USERNAME, JMSConMessType.PASSWORD, JMSConMessType.BROKEURL);
 
        try {
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            connection.start(); //启动连接
            // 创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建一个连接HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            // 创建消息消费者
            messageConsumer = session.createConsumer(destination);
            
            getMessage(messageConsumer);
 
        } catch (JMSException e) {
            e.printStackTrace();
        }
 
    }
    
    /**
     * 接收消息
     * @param messageConsumer
     * @throws JMSException
     */
    public static void getMessage(MessageConsumer messageConsumer) throws JMSException {
    	Message message = messageConsumer.receive();
        
        if (message instanceof ObjectMessage) {  
        	ObjectMessage objMessage = (ObjectMessage) message;
        	Student stu = (Student) objMessage.getObject();
            System.out.println("接收信息： " + stu.toString()); 
        } 
        
        if (message instanceof MapMessage) {
        	MapMessage mapMessage = (MapMessage) message;
        	String name = mapMessage.getString("name");
        	String company = mapMessage.getString("company");
        	System.out.println("name = " + name);
        	System.out.println("company = " + company);
        }
        
        if (message instanceof BytesMessage) {
        	BytesMessage bytesMessage = (BytesMessage) message;
        	byte[] b = new byte[1024];
            int len = -1;
            while ((len = bytesMessage.readBytes(b)) != -1) {
              System.out.println(new String(b, 0, len));
            }
        }
        
        if (message instanceof StreamMessage) {
        	StreamMessage streamMessage = (StreamMessage) message;
        	String str = streamMessage.readString();
        	int num = streamMessage.readInt();
        	System.out.println(str + ", " + num);
        }
    }
}

