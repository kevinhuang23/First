package Jdbc;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//声明是id=customerService的Bean

@Service("customerService")
public class customerServiceImp implements customerService {
	//获取id=custDAO的Bean并注入
    @Resource(name="custDAO")
	private custDAO custDAO;
    
	@Transactional(isolation=Isolation.READ_UNCOMMITTED,propagation= Propagation.REQUIRED)
	public void addCustomer(customer cs) {
		custDAO.insert(cs);		
	}
    
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void searchByName(String name) {
		System.out.println(custDAO.selectByName("1",name).getCustId()+"well"+custDAO.selectByName("1",name).getCustName());		
	}
	//对于某些异常不回滚：noRollbackFor={ArithmeticException.class}
	//超时回滚：timeout=3  
	@Transactional(isolation=Isolation.READ_UNCOMMITTED,propagation= Propagation.NOT_SUPPORTED)
	public void ExchangeCustomer(customer cs1,customer cs2) {
		custDAO.update(cs1);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=5/0;
		custDAO.update(cs2);	
	}

	

}
