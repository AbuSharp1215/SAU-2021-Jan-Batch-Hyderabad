package com.sharp.main.service;

import com.sharp.main.model.Item;
import com.sharp.main.model.Order;

public interface OrderService {
	
	public boolean createOrder(Order order);
	
	public Order viewOrder(Integer orderId);
	
	public boolean addItemIfNotExist(Item item, Order order);
	
	public boolean updateItemIfExist(Item item, Order order);
	
	public boolean deleteItemIfExist(Integer itemId, Order order);

}
