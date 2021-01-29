package com.sharp.main.controller;

import java.util.logging.Logger;

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


	Logger log = Logger.getLogger(OrderController.class.getName());
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createOrder(@RequestBody Order order){
		boolean response=false;
		if(order == null) {
			log.severe("Request body is null");
			return new ResponseEntity<Object>("Please enter request body",HttpStatus.BAD_REQUEST);
		}
		
		if(order.getOrderId()<=0) {
			log.severe("Orderid less than zero");
			return new ResponseEntity<Object>("Orderid must be greater than zero",HttpStatus.BAD_REQUEST);
		}
		
		if(order.getItemList() == null) {
			log.severe("Orderid less than zero");
			return new ResponseEntity<Object>("Please enter atleast one item to create your order",HttpStatus.BAD_REQUEST);
		}
		
		response = orderService.createOrder(order);
		
		log.info(response?"Success":"");
		
		if(!response) {
			log.warning("Order not created, check the service");
			return new ResponseEntity<Object>("Order not created",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>("Order created successfully",HttpStatus.OK);
	}
	
	@PostMapping("/item/add/{orderId}")
	public ResponseEntity<Object> addItem(
			@RequestBody Item item, @PathVariable Integer orderId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order == null) {
			log.info("No orders found");
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		boolean success = orderService.addItemIfNotExist(item, order);
		
		log.info(success?"Success":"");
		
		if(!success) {
			log.warning("Item id is already exist");
			return new ResponseEntity<Object>("Item id is already exist",HttpStatus.FOUND);
		}
		
		return new ResponseEntity<Object>("Item added successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/item/delete/{orderId}/{itemId}")
	public ResponseEntity<Object> deleteItem(
			@PathVariable Integer orderId, @PathVariable Integer itemId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order == null) {
			log.info("No orders found");
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		boolean success = orderService.deleteItemIfExist(itemId, order);
		log.info(success?"Success":"");
		
		if(!success) {
			log.warning("Item id not found");
			return new ResponseEntity<Object>("Item id not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>("Item deleted successfully",HttpStatus.OK);
	}
	
	@PutMapping("/item/update/{orderId}")
	public ResponseEntity<Object> updateItem(
			@RequestBody Item item, @PathVariable Integer orderId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order == null) {
			log.info("No orders found");
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		boolean success = orderService.updateItemIfExist(item, order);
		log.info(success?"Success":"");
		
		if(!success) {
			log.warning("Item id not found");
			return new ResponseEntity<Object>("Item id not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>("Item Updates successfully",HttpStatus.OK);
	}
	
	@GetMapping("/get/{orderId}")
	public ResponseEntity<Object> viewOrder(@PathVariable Integer orderId){
		
		Order order = orderService.viewOrder(orderId);
		
		if(order==null) {
			log.info("No orders found");
			return new ResponseEntity<Object>("Order not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(order,HttpStatus.OK);
	}

}
