package Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


public class customerImp implements customerDAO {

	private DataSource dataSource;
    
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public void insert(customer customer) throws SQLException {
		// TODO Auto-generated method stub
		//得到数据库链接
		Connection cn=dataSource.getConnection();
		PreparedStatement pr =cn.prepareStatement("insert into customer(custNo,custname) values (?,?)");
		//设置参数
		pr.setString(2, customer.getCustName());
		pr.setString(1, customer.getCustId());
		//提交语句
		pr.executeUpdate();		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	

}
