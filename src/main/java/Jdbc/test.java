package Jdbc;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

	public static void main(String[] args) throws SQLException {
		 ApplicationContext ts=new ClassPathXmlApplicationContext("Jdbc/bean.xml");
		 customer cs=new customer("003","khh");
	     customerImp cI=ts.getBean("custTemplate",customerImp.class);	     
	     cI.insert(cs);
//	     custDAO cust=ts.getBean("custDAO",custDAO.class);
//	     //通过JDBCTemplate方式插入
//	     cust.insert(cs);
//	     List<customer> ls =new ArrayList<>();
//	     //通过JDBCTemplate方式查询
//         ls=cust.selectAll("khh");
//         for(customer bs:ls) {
//	     System.out.println(bs.getCustId()+"it"+bs.getCustName());
//	     }
	     

	}

}
