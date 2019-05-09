package communicate;
/*
 * 最基本的控制台读入，打印
 * 读TXT写TXT
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Io {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         String a="kevink";
         String encoding="UTF-8";
         String filePath="/Users/huangcheng/Downloads/write.txt";
         Io io=new Io();
         System.out.println(io.getClass().getClassLoader().getResource("").getPath());
         System.out.println(io.getClass().getResource("/communicate/contact.xml").getPath());
         System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
         
         
         BufferedReader bu=new BufferedReader(new InputStreamReader(System.in));
         try {
			a=bu.readLine();
			contentToTxt(filePath,a);
			BufferedReader readB=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),encoding));
			while((a=readB.readLine())!=null) {
				System.out.println(a);
			}
			readB.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}
	public static void contentToTxt(String filePath, String content) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath),true));
            writer.write("\n"+content);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
