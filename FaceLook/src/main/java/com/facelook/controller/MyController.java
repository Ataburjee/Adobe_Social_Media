package com.facelook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/get")
	ResponseEntity<String> get(){
		return new ResponseEntity<>("welcone get...!",HttpStatus.OK);
	}
	
	@PostMapping("/post")
	ResponseEntity<String> post(){
		return new ResponseEntity<>("welcone post request...!",HttpStatus.OK);
	}
	
}
