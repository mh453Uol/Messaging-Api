package org.majid.JXRS_Jersey.bean;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {
	private @QueryParam("year") Integer year;
	private @QueryParam("offset") Integer offset; 
	private @QueryParam("amount") Integer amount;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
