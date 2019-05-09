package jaxb;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * function:Jaxb2 完成Java和XML的编组、解组
 * @author hoojo
 * @createDate 2011-4-25 上午11:54:06
 * @file Jaxb2Test.java
 * @package com.hoo.test
 * @project WebHttpUtils
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class Jaxb2Test {
    private JAXBContext context = null;
    
    private StringWriter writer = null;
    private StringReader reader = null;
    
    private AccountBean bean = null;
    
    @Before
    public void init() {
        bean = new AccountBean();
        bean.setAddress("北京");
        bean.setEmail("email");
        bean.setId(1);
        bean.setName("jack");
//        Birthday day = new Birthday();
//        day.setBirthday("2010-11-22");
//        bean.setBirthday(day);
        
        try {
            context = JAXBContext.newInstance(AccountBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void destory() {
        context = null;
        bean = null;
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.gc();
    }
    
    
    @Test
    public void testBean2XML() {
        try {
            //下面代码演示将对象转变为xml
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            writer = new StringWriter();
            mar.marshal(bean, writer);
            fail(writer);
            
            //下面代码演示将上面生成的xml转换为对象
            reader = new StringReader(writer.toString());
            Unmarshaller unmar = context.createUnmarshaller();
            bean = (AccountBean)unmar.unmarshal(reader);
            fail(bean);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public void fail(Object o) {
        System.out.println(o);
    }
    
    public void failRed(Object o) {
        System.err.println(o);
    }
}

