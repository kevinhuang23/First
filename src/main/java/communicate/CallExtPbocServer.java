package communicate;


import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class CallExtPbocServer {
 
	public static void main(String[] args) {
		int serverPort = 7896;
		ServerSocket listenSocket ;
		Socket clientSocket ;
		try {
//			******************接收读取客户端传来的内容******************************************
				listenSocket = new ServerSocket(serverPort);
			    clientSocket = listenSocket.accept();
			    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			    byte[] buf=new byte[1024];
			    int tag=in.read(buf);
			    if(tag!=-1){
			    	System.out.println(new String(buf,0,tag));
			    }
			    in.close();
		}catch(Exception e){
			
		}
		
	}
 
}

