package com.saint_augustin.project.controller;



import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class DossiersControllers {

	@RequestMapping(value = "/authentification")
	public Authentication authentication (Authentication authentication) {
		System.out.println(authentication);
		return authentication;
	}
	
	
}
