package com.github.java.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.github.java.domain.ResourceDomain;

@Service
public class ResourceService {
	
	public ResourceDomain  getUnauthorizedResourceData(ResourceDomain resourceDomain) {
		resourceDomain.setCurrentDate(new Date());
		resourceDomain.setResourceName("Unauthorized resource");
		return resourceDomain;
	}
	
	public ResourceDomain  getAuthorizedResourceData(ResourceDomain resourceDomain) {
		resourceDomain.setCurrentDate(new Date());
		resourceDomain.setResourceName("Authorized resource");
		return resourceDomain;
	}
}
