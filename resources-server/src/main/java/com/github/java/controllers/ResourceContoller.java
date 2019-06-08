package com.github.java.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.java.domain.ResourceDomain;
import com.github.java.services.ResourceService;

@RestController
public class ResourceContoller {

	@Autowired
	private ResourceService resourceService;

	@GetMapping("/preauthorize")
	@PreAuthorize("#oauth2.isOAuth() and #oauth2.hasScope('GITHUB') or #oauth2.hasScope('GIT')")
	public ResourceDomain resourceMethodWithPreAuthorize(Principal auth) {
		ResourceDomain resourceDomain = new ResourceDomain();
		return resourceService.getAuthorizedResourceData(resourceDomain);
	}

	@GetMapping("/noauthorization")
	public ResourceDomain resourceMethtodWithoutPreAuthorize(Principal auth) {
		ResourceDomain resourceDomain = new ResourceDomain();
		return resourceService.getUnauthorizedResourceData(resourceDomain);
	}
}
