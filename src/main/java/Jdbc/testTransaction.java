package Jdbc;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testTransaction {

	public static void main(String[] args) throws SQLException {
		 ApplicationContext ts=new ClassPathXmlApplicationContext("Jdbc/bean2.xml");
		 customer cs=new customer("002","kevinhuang");
		 customerService cService=ts.getBean("customerService",customerServiceImp.class);
//		 cService.addCustomer(cs);
		 cService.searchByName("java");
		 
	     List<customer> ls =new ArrayList<>();
	     custDAO cust=ts.getBean("custDAO",custDAO.class);
	     //通过JDBCTemplate方式查询
         ls=cust.selectAll("khh");
         for(customer bs:ls) {
	     System.out.println(bs.getCustId()+"it"+bs.getCustName());
	     }
	     //测试事务
         customer cs1=new customer("002","westBrook");
         cs1.setCurbal(new BigDecimal("500"));
         customer cs2=new customer("002","kevinhuang");
         cs2.setCurbal(new BigDecimal("300"));
         cService.ExchangeCustomer(cs1, cs2);

	}

}
