package communicate;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
 
public class CallApsService {
	private Socket s;
	private DataOutputStream out;
 
	public CallApsService() throws IOException {
	}
 
	public static void main(String[] args) throws Exception {
		CallApsService c = new CallApsService();
		int i = 0;
		Map<String, String> infoMap = initInput();
		for(Entry<String, String> enty: infoMap.entrySet()){
			i++;
			c.talk(enty.getKey(),enty.getValue(),i);
			System.out.println("发送信息完毕!");
		}
		
	}
 
	// 发送对象
	// ObjectOutputStream oos;
	// TransferObj obj;
	public void sendMessage(Socket s,String idNo,String name,int i) {
		try {
 
			// socket传字符串
			out = new DataOutputStream(s.getOutputStream());
			//批次号申请
			String xmlReq = "<SERVICE xmlns=\"http://www.asss.com/dataspec/\"><SERVICE_HEADER><SERVICE_SN>198234712374-17412748</SERVICE_SN><SERVICE_ID>12100</SERVICE_ID><ORG>org1</ORG><CHANNEL_ID>01</CHANNEL_ID><OP_ID>tt</OP_ID><REQUST_TIME>20050621123000</REQUST_TIME><VERSION_ID>01</VERSION_ID><MAC/></SERVICE_HEADER><SERVICE_BODY><EXT_ATTRIBUTES><AUTH><Q_PIN>0123456789ABCDEF</Q_PIN></AUTH></EXT_ATTRIBUTES><REQUEST><SYS_ID>aps</SYS_ID><OPERATOR_ID>tt</OPERATOR_ID><ID_NO>123</ID_NO><ID_TYPE>I</ID_TYPE><NAME>czy</NAME></REQUEST></SERVICE_BODY></SERVICE>";
			
			
			String lenthEncode = "UTF-8";
			long len = xmlReq.getBytes(lenthEncode).length;
			String length=String.format("%06d",len);
			xmlReq = length+ xmlReq;
			byte[] bt = xmlReq.getBytes(lenthEncode);
			out.write(bt);
 
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
 
	public void talk(String idNo,String name,int i) throws Exception {
		try {
			// 发送对象
			s = new Socket("127.0.0.1", 7896);
			System.out.println("客户端:发送信息");
			sendMessage(s,idNo,name, i);
			System.out.println("发送信息完毕!");
			// 发字符串
//			 receiveMessage(s);
			out.close();
			// in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null)
					s.close(); // 断开连接
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static Map<String, String> initInput(){
		
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("140106198506020369","湘一百二");
		
		return infoMap;
	}
	
	
}
