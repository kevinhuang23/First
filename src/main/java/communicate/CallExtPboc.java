package communicate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;
 

public class CallExtPboc {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Socket soc = null;
		String data = "";
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		int serverPort = 7896;
		soc = new Socket(addr, serverPort);
		DataOutputStream out=new DataOutputStream(soc.getOutputStream());
		String xmlReq = builderReqXml();
		byte[] b = xmlReq.getBytes("UTF-8");
		long len=b.length;
		String length=String.format("%06d",len);
		 xmlReq = length+xmlReq;
		byte[] bt = xmlReq.getBytes("utf-8");
		 out.write(bt);
//		out.writeUTF(builderReqXml());
		//接收来自服务器的字节类型数据，并解析打印
		//DataInputStream in = new DataInputStream(soc.getInputStream());
		//byte[] buf =new byte[1024];
		//int readb=in.read(buf);
		//System.out.println("**************************************");
		//if(readb != -1){
		//	System.out.println(new String(buf,0,readb));
		//}
		//in.close();
	}
	
	private static String builderReqXml(){
		StringBuffer xmlReq = new StringBuffer();
//		xmlReq.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlReq.append("<SERVICE xmlns=\"http://www.sss.com/dataspec/\">");
		//构造SERVICE_HEADER节点里面的数据
		StringBuilder serviceHeader = new StringBuilder();
		serviceHeader.append(getNode("SERVICE_SN","198234712374-17412748"));
		serviceHeader.append(getNode("SERVICE_ID","12100"));//交易码
		serviceHeader.append(getNode("ORG","org1"));
		serviceHeader.append(getNode("CHANNEL_ID","01"));//服务渠道编号----请求渠道
		serviceHeader.append(getNode("OP_ID","tt"));
		serviceHeader.append(getNode("REQUST_TIME","20050621123000"));
		serviceHeader.append(getNode("VERSION_ID","01"));
		serviceHeader.append("<MAC/>");
		//将SERVICE_HEADER节点数据加入报文中
		xmlReq.append(getNode("SERVICE_HEADER", serviceHeader.toString()));
		
		//构造SERVICE_BODY节点里面的数据
		StringBuilder serviceBody = new StringBuilder();
		//构造extAttributes节点的数据
		StringBuffer extAttributes =new StringBuffer();
		//构造auth节点数据
		StringBuffer auth =new StringBuffer();
		auth.append(getNode("Q_PIN", "0123456789ABCDEF"));
		//将auth节点加入到EXT_ATTRIBUTES中
		extAttributes.append(getNode("AUTH", auth.toString()));
		//将extAttributes节点加入到SERVICE_BODY中
		serviceBody.append(getNode("EXT_ATTRIBUTES", extAttributes.toString()));
		//构造REQUEST节点里面的数据
		StringBuilder request = new StringBuilder();
		request.append(getNode("SYS_ID","aps"));
		request.append(getNode("OPERATOR_ID", "tt"));
		request.append(getNode("ID_NO", "123"));
		request.append(getNode("ID_TYPE", "I"));
		request.append(getNode("NAME", "czy"));
		//将REQUEST节点数据加入至SERVICE_BODY中
		serviceBody.append(getNode("REQUEST", request.toString()));
		//将SERVICE_BODY节点数据加入至报文中EXT_ATTRIBUTES
		xmlReq.append(getNode("SERVICE_BODY", serviceBody.toString()));
		xmlReq.append("</SERVICE>");
		System.out.println(xmlReq);
		return xmlReq.toString();
	}
	
	/**
	 * 根据节点名称以及节点内容，返回节点字符串
	 * @param nodeName 节点名称
	 * @param nodeValue 节点内容
	 * @return	构造出的节点字符串
	 */
	private static String getNode(String nodeName,String nodeValue){
		StringBuilder node = new StringBuilder();
		if(!StringUtils.isEmpty(nodeName)){
			node.append("<"+nodeName+">");
			if(!StringUtils.isEmpty(nodeValue)){
				node.append(nodeValue);
			}
			node.append("</"+nodeName+">");
		}
		return node.toString();
	}
}

