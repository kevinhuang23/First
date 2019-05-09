package Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class custDAO {
	private JdbcTemplate jd = null;

	public void setJdbcTemplate(JdbcTemplate jd) {
		this.jd = jd;
	}

	public void insert(customer cs) {
		jd.update("insert into customer(custNo,custname) values (?,?)", cs.getCustId(), cs.getCustName());
        
	}
	
	public void update(customer cs) {
		jd.update("update customer set curbal = ? where custname = ?", cs.getCurbal(),cs.getCustName());
        
	}

	public customer selectByName(String id, String name) {
		return jd.queryForObject("select custNo,custname from customer where custname =?", new Object[] { name },
				new myRowMapper());

	}

	public List<customer> selectAll(String name) {
		return jd.query("select custNo,custname from customer where custname =?", new Object[] { name },
				new myRowMapper());

	}

	class myRowMapper implements RowMapper<customer> {

		@Override
		public customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			customer cs = new customer();
			cs.setCustId(rs.getString("custNo"));
			cs.setCustName(rs.getString("custname"));
			return cs;
		}

	}
}
