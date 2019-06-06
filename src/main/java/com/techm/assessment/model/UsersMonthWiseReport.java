package com.techm.assessment.model;

import java.io.Serializable;

public class UsersMonthWiseReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mon;
	private Long count;

	public UsersMonthWiseReport(String mon, Long count) {
		this.mon = mon;
		this.count = count;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}