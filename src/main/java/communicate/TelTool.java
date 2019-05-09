package communicate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class TelTool {
    String filePath=this.getClass().getResource("/communicate/contact.xml").getPath();
    
    //添加联系人
    public void addNum(String name,String num) throws DocumentException, IOException{
    	    System.out.println(filePath);
        //创建解析器对象
        SAXReader reader =new SAXReader();
        Document doc = reader.read(new FileInputStream(filePath));
        //获取根标签
        Element roele = doc.getRootElement();
        //添加子标签
        Element p = roele.addElement("person");
            p.addAttribute("id", name);
        Element na = p.addElement("name");
            na.addText(name);
        Element number = p.addElement("num");
            number.addText(num);
        System.out.println("添加成功");
        //写入文件
        OutputFormat prettyPrint =OutputFormat.createPrettyPrint();
        XMLWriter w=new XMLWriter(new FileOutputStream(filePath),prettyPrint);
        w.write(doc);
        w.close();
    }   


    //编辑联系人
    public void setNum(String name,String num) throws DocumentException, IOException{
        SAXReader reader=new SAXReader();
        Document doc=reader.read(new FileInputStream(filePath));
        //找到指定person子标签
        Element ele = (Element)doc.selectSingleNode("//person[@id='"+name+"']");
        Element number =ele.element("num"); 
        number.setText(num);
        System.out.println("修改成功");
        //写入文件
        OutputFormat prettyPrint =OutputFormat.createPrettyPrint();
        XMLWriter w=new XMLWriter(new FileOutputStream(filePath),prettyPrint);
        w.write(doc);
        w.close();      
    }


    //删除联系人
    public void delNum(String name) throws DocumentException, IOException{
        SAXReader reader=new SAXReader();
        Document doc=reader.read(new FileInputStream(filePath));
        //找到指定person子标签
        Element ele = (Element)doc.selectSingleNode("Person//person[@id='"+name+"']");
        //删除
        ele.detach();
        System.out.println("删除成功");
        //写入文件
        OutputFormat prettyPrint =OutputFormat.createPrettyPrint();
        XMLWriter w=new XMLWriter(new FileOutputStream(filePath),prettyPrint);
        w.write(doc);
        w.close();      
    }


    //查询所有联系人
    public void queryAll() throws IOException, DocumentException{
        SAXReader reader=new SAXReader();
        Document doc=reader.read(new FileInputStream(filePath));
        //通过迭代器获取所有子标签
        Iterator<Element> iterator = doc.getRootElement().elementIterator("person");
        while(iterator.hasNext()){
            Element next = iterator.next();
            String name = next.element("name").getText();
            String num = next.element("num").getText();
            System.out.println("姓名:"+name+"--"+"电话:"+num);
        }   

    }   

}

