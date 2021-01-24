package com.sharp.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("/")
	public String showRootPath() {
		return "Welcome to Root Page";
	}
	
//	@GetMapping("/welcome")
//	public String showWelcomeMessage() {
//		return "Welcome to Home Page";
//	}
//	
//	@GetMapping("/hello")
//	public String showHello() {
//		return "Hello World";
//	}
}
