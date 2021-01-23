package com.sharp.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.main.model.Item;
import com.sharp.main.model.Order;
import com.sharp.main.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createOrder(@RequestBody Order order){
		String response="";
		if(order!=null)
			response = orderService.createOrder(order);
		else {
			return new ResponseEntity<Object>("Please enter request body",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	@PostMapping("/item/add/{orderId}")
	public ResponseEntity<Object> addItem(
			@RequestBody Item item, @PathVariable Integer orderId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order == null) {
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		boolean success = orderService.addItemIfNotExist(item, order);
		
		if(!success) {
			return new ResponseEntity<Object>("Item id is already exist",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>("Item added successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/item/delete/{orderId}/{itemId}")
	public ResponseEntity<Object> deleteItem(
			@PathVariable Integer orderId, @PathVariable Integer itemId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order == null) {
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		boolean success = orderService.deleteItemIfExist(itemId, order);
		
		if(!success) {
			return new ResponseEntity<Object>("Item id not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>("Item deleted successfully",HttpStatus.OK);
	}
	
	@PutMapping("/item/update/{orderId}")
	public ResponseEntity<Object> updateItem(
			@RequestBody Item item, @PathVariable Integer orderId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order == null) {
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		boolean success = orderService.updateItemIfExist(item, order);
		
		if(!success) {
			return new ResponseEntity<Object>("Item id not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>("Item Updates successfully",HttpStatus.OK);
	}
	
	@GetMapping("/get/{orderId}")
	public ResponseEntity<Object> viewOrder(@PathVariable Integer orderId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order==null) {
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(order,HttpStatus.OK);
	}

}
