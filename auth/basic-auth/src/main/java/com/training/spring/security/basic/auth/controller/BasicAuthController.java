package com.training.spring.security.basic.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class BasicAuthController {
	private static final Logger logger = LoggerFactory.getLogger(BasicAuthController.class);
	
	@GetMapping("/test/{id}")
	public ResponseEntity<String> testEndPoint(@PathVariable("id") String id) {
		logger.info("---at testing end point----");
		return new ResponseEntity<String>("test user id :"+id, HttpStatus.OK);
	}

	@GetMapping("/auth/{id}")
	public ResponseEntity<String> getOperation(@PathVariable("id") String id) {
		logger.info("---at getOperation end point for USER role-------");
		return new ResponseEntity<String>(" regular user id :" + id, HttpStatus.OK);
	}

	@GetMapping("/admin/{id}")
	public ResponseEntity<String> getOperationForAdminRole(@PathVariable("id") String id) {
		logger.info("---at getOperation end point for ADMIN role-------");
		return new ResponseEntity<String>(" admin user id :" + id, HttpStatus.OK);
	}
	
}
