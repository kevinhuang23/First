package communicate;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
 
public class TCPServer2 {
	public static void main(String args[]) {
			int serverPort = 7896;
			ServerSocket listenSocket ;
			Socket clientSocket ;
			try {
//				******************接收读取客户端传来的内容******************************************
					listenSocket = new ServerSocket(serverPort);
				    clientSocket = listenSocket.accept();
				    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
					String data = in.readUTF();
//					System.out.println("接收到的数据:" + data);
					DOM(data);
					
//					******************向客户端写xml文件******************************************
					DataOutputStream out=new DataOutputStream(clientSocket.getOutputStream());
					// 定义工厂 API，使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器
					DocumentBuilderFactory factory = DocumentBuilderFactory
							.newInstance();
					// 定义 API， 使其从 XML 文档获取 DOM 文档实例。使用此类，应用程序员可以从 XML 获取一个 Document
					DocumentBuilder builder = factory.newDocumentBuilder();
					// Document 接口表示整个 HTML 或 XML 文档。从概念上讲，它是文档树的根，并提供对文档数据的基本访问
					Document document = builder.newDocument();
					//组织生产xml文件内容
					Element root = document.createElement("persons");
					document.appendChild(root);
					Element person = document.createElement("person");
					Element name = document.createElement("name");
					name.appendChild(document.createTextNode("java小强"));
					person.appendChild(name);
					Element sex = document.createElement("sex");
					sex.appendChild(document.createTextNode("man"));
					person.appendChild(sex);
					Element age = document.createElement("age");
					age.appendChild(document.createTextNode("99"));
					person.appendChild(age);
					root.appendChild(person);
	
					TransformerFactory tf = TransformerFactory.newInstance();
					// 此抽象类的实例能够将源树转换为结果树
					Transformer transformer;
					transformer = tf.newTransformer();
	
					DOMSource source = new DOMSource(document);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					transformer.transform(source, new StreamResult(bos));
					String xmlStr = bos.toString();
					out.writeUTF(xmlStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void DOM(String data) {
		try {
			//遍历xml获取内容
			byte[] b = data.getBytes();
			InputStream inp = new ByteArrayInputStream(b);
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inp);
			NodeList nl = doc.getElementsByTagName("persons");
//			System.out.println(nl.getLength());
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.println("person:  "
						+ doc.getElementsByTagName("person").item(i)
								.getFirstChild().getNodeValue());
				System.out.println("name:  "
						+ doc.getElementsByTagName("name").item(i)
								.getFirstChild().getNodeValue());
				System.out.println("sex:  "
						+ doc.getElementsByTagName("sex").item(i)
								.getFirstChild().getNodeValue());
				System.out.println("age:  "
						+ doc.getElementsByTagName("age").item(i)
								.getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

