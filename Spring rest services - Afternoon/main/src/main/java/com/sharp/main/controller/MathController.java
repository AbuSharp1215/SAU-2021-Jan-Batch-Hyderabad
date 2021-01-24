package com.sharp.main.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/math")
public class MathController {
	
	
	//add?num1=5&num2=10
	@GetMapping("/add")
	public Integer add(@RequestParam("num1") Integer n1,@RequestParam("num2") Integer n2) {
		
		return n1+n2;
	}
	
	//multiply/5/6
	@GetMapping("/multiply/{num1}/{num2}")
	public Integer multiply(@PathVariable("num1") Integer n1,@PathVariable("num2") Integer n2) {
		
		return n1*n2;
	}

}
