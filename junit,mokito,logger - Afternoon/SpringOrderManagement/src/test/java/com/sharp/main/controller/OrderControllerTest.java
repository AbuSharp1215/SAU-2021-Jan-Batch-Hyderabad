package com.sharp.main.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sharp.main.model.Item;
import com.sharp.main.model.Order;
import com.sharp.main.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderService orderService;
	
	@Nested
	class CreateOrderTest{
		
		@Test
		void testCreateOrder() {
			
			Order order = new Order();
			order.setOrderId(1);
			
			Item item = new Item();
			item.setItemId(1);
			item.setItemName("Samsung M21");
			item.setItemDescription("Brand new mobile from samsung");
			item.setPrice("Rs.17000");
			item.setQuantity(5);
			
			List<Item> itemList = new ArrayList<>();
			itemList.add(item);
			
			order.setItemList(itemList);
			
			Mockito.when(orderService.createOrder(Mockito.anyObject())).thenReturn(true);
			
			
			ResponseEntity<Object> response = orderController.createOrder(order);
			
			assertEquals("Order created successfully", response.getBody());
			Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		}
		
		@Test
		void testCreateNotOrder() {
			
			Order order = new Order();
			order.setOrderId(1);
			
			Item item = new Item();
			item.setItemId(1);
			item.setItemName("Samsung M21");
			item.setItemDescription("Brand new mobile from samsung");
			item.setPrice("Rs.17000");
			item.setQuantity(5);
			
			List<Item> itemList = new ArrayList<>();
			itemList.add(item);
			
			order.setItemList(itemList);
			
			Mockito.when(orderService.createOrder(Mockito.anyObject())).thenReturn(false);
			
			
			ResponseEntity<Object> response = orderController.createOrder(order);
			
			assertEquals("Order not created", response.getBody());
			Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
		}
		
		@Test
		void testCreateOrder_nullCheck() {
			
			ResponseEntity<Object> response = orderController.createOrder(null);
			
			Assertions.assertEquals("Please enter request body", response.getBody());
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testCreateOrder_orderIdNegative() {
			
			Order order = new Order();
			order.setOrderId(-1);
			
			Item item = new Item();
			item.setItemId(1);
			item.setItemName("Samsung M21");
			item.setItemDescription("Brand new mobile from samsung");
			item.setPrice("Rs.17000");
			item.setQuantity(5);
			
			List<Item> itemList = new ArrayList<>();
			itemList.add(item);
			
			order.setItemList(itemList);
			
			ResponseEntity<Object> response = orderController.createOrder(order);
			
			Assertions.assertEquals("Orderid must be greater than zero", response.getBody());
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testCreateOrder_nullCheckItem() {
			
			Order order = new Order();
			order.setOrderId(1);
			
			ResponseEntity<Object> response = orderController.createOrder(order);
			
			Assertions.assertEquals("Please enter atleast one item to create your order", response.getBody());
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
			
		}
		
	}
	
	@Nested
	class AddItemTest{
		
		@Test
		void testAddItem() {
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
				
			Item item = new Item();
			item.setItemId(2);
			item.setItemName("Redmi Note 9 Pro");
			item.setItemDescription("Redmi mobiles with gorilla glass 5");
			item.setPrice("Rs.19000");
			item.setQuantity(2);
			
			Mockito.when(orderService.addItemIfNotExist(Mockito.anyObject(), Mockito.anyObject())).thenReturn(true);
			
			ResponseEntity<Object> response = orderController.addItem(item, 1);

			Assertions.assertEquals("Item added successfully", response.getBody());
			Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testAddItem_nullOrder() {
						
			Item item = new Item();
			item.setItemId(2);
			item.setItemName("Redmi Note 9 Pro");
			item.setItemDescription("Redmi mobiles with gorilla glass 5");
			item.setPrice("Rs.19000");
			item.setQuantity(2);
			
			ResponseEntity<Object> response = orderController.addItem(item, 1);

			Assertions.assertEquals("Order not found", response.getBody());
			Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testAddItem_itemExist() {
						
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
			
			Item item = new Item();
			item.setItemId(1);
			item.setItemName("Redmi Note 9 Pro");
			item.setItemDescription("Redmi mobiles with gorilla glass 5");
			item.setPrice("Rs.19000");
			item.setQuantity(2);
			
			Mockito.when(orderService.addItemIfNotExist(Mockito.anyObject(), Mockito.anyObject())).thenReturn(false);
			
			ResponseEntity<Object> response = orderController.addItem(item, 1);

			Assertions.assertEquals("Item id is already exist", response.getBody());
			Assertions.assertEquals(HttpStatus.FOUND.value(), response.getStatusCodeValue());
			
		}
		
	}
	
	@Nested
	class ViewOrderTest{
		
		@Test
		void testViewOrder() {
			
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
			
			ResponseEntity<Object> response = orderController.viewOrder(1);
			
			Assertions.assertEquals(order, response.getBody());
			Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		}
		
		@Test
		void testViewOrder_notFound() {
					
			ResponseEntity<Object> response = orderController.viewOrder(1);
			
			Assertions.assertEquals("Order not found", response.getBody());
			Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
		}
		
	}
	
	@Nested
	class UpdateItemTest{
		
		@Test
		void testUpdateItem() {
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
				
			Item item = new Item();
			item.setItemId(2);
			item.setItemName("Redmi Note 8");
			item.setItemDescription("Redmi mobiles with gorilla glass 5");
			item.setPrice("Rs.10000");
			item.setQuantity(2);
			
			Mockito.when(orderService.updateItemIfExist(Mockito.anyObject(), Mockito.anyObject())).thenReturn(true);
			
			ResponseEntity<Object> response = orderController.updateItem(item, 1);

			Assertions.assertEquals("Item Updates successfully", response.getBody());
			Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testUpdateItem_nullOrder() {
						
			Item item = new Item();
			item.setItemId(2);
			item.setItemName("Redmi Note 8");
			item.setItemDescription("Redmi mobiles with gorilla glass 5");
			item.setPrice("Rs.10000");
			item.setQuantity(2);
			
			ResponseEntity<Object> response = orderController.updateItem(item, 1);

			Assertions.assertEquals("Order not found", response.getBody());
			Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testUpdateItem_itemNotFound() {
						
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
			
			Item item = new Item();
			item.setItemId(2);
			item.setItemName("Redmi Note 8");
			item.setItemDescription("Redmi mobiles with gorilla glass 5");
			item.setPrice("Rs.10000");
			item.setQuantity(2);
			
			Mockito.when(orderService.updateItemIfExist(Mockito.anyObject(), Mockito.anyObject())).thenReturn(false);
			
			ResponseEntity<Object> response = orderController.updateItem(item, 1);

			Assertions.assertEquals("Item id not found", response.getBody());
			Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
			
		}
		
	}
	
	
	@Nested
	class DeleteItemTest{
		
		@Test
		void testDeleteItem() {
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
						
			Mockito.when(orderService.deleteItemIfExist(Mockito.anyInt(), Mockito.anyObject())).thenReturn(true);
			
			ResponseEntity<Object> response = orderController.deleteItem(1, 1);

			Assertions.assertEquals("Item deleted successfully", response.getBody());
			Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testDeleteItem_nullOrder() {
			
			ResponseEntity<Object> response = orderController.deleteItem(1, 1);

			Assertions.assertEquals("Order not found", response.getBody());
			Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
			
		}
		
		@Test
		void testDeleteItem_itemNotFound() {
						
			Order order = new Order();
			Mockito.when(orderService.viewOrder(Mockito.anyInt())).thenReturn(order);
			
			Mockito.when(orderService.deleteItemIfExist(Mockito.anyInt(), Mockito.anyObject())).thenReturn(false);
			
			ResponseEntity<Object> response = orderController.deleteItem(1, 1);

			Assertions.assertEquals("Item id not found", response.getBody());
			Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
			
		}
		
	}
	
	

}
