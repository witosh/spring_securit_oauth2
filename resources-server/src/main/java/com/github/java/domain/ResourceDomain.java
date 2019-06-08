package com.github.java.domain;

import java.util.Date;

public class ResourceDomain {

	private Date currentDate;
	private String resourceName;

	public ResourceDomain() {
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
}
