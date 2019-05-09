package Jdbc;

import java.math.BigDecimal;

public class customer {
	private String custId;
	private String custName;
	private BigDecimal curbal;
	public customer() {
		
	}
	public customer(String custId,String custName){
		this.custId=custId;
		this.custName=custName;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public BigDecimal getCurbal() {
		return curbal;
	}
	public void setCurbal(BigDecimal curbal) {
		this.curbal = curbal;
	}

}
