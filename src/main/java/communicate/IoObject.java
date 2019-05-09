package communicate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jms.Student;

//java转换为Byte数组
public class IoObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath="/Users/huangcheng/Downloads/write.txt";
		Student student1=new Student("001","kevin");
		//Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(student1);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        File file = new File(filePath);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            Student newUser = (Student) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        
        
	}

}
