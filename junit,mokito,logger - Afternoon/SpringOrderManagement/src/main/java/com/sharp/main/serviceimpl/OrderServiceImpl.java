package com.sharp.main.serviceimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;


import com.sharp.main.model.Item;
import com.sharp.main.model.Order;
import com.sharp.main.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

	List<Order> orderList = new ArrayList<>();
	
	@Override
	public boolean createOrder(Order order) {
	
		return orderList.add(order);
		
	}

	@Override
	public Order viewOrder(Integer orderId) {
		Optional<Order> order = orderList.stream().filter(o -> orderId == o.getOrderId()).findFirst();
		
		if(order.isPresent()) {
			log.info("Order is present in orderlist");
			return order.get();
		}
		log.warning("No orders found");
		return null;
	}

	@Override
	public boolean addItemIfNotExist(Item item, Order order) {
		
		if(checkIteminOrder(item.getItemId(), order)) {
			log.warning("item found in order");
			return false;
		}
		
		orderList.get((order.getOrderId()-1)).getItemList().add(item);
		log.info("item added -> success");
		return true;
	}

	@Override
	public boolean updateItemIfExist(Item item, Order order) {
		
		if(!checkIteminOrder(item.getItemId(), order)) {
			log.warning("item not found in order");
			return false;
		}
		//orderList.get((order.getOrderId()-1)).getItemList().remove((item.getItemId()-1));
		orderList.get((order.getOrderId()-1)).getItemList().set((item.getItemId()-1), item);
		log.info("item updated -> success");
		return true;
	}
	
	public boolean checkIteminOrder(Integer itemId, Order order) {
		
		log.info("checking item in order....");
		Optional<Item> filterItem = order.getItemList().stream().filter(i -> itemId == i.getItemId()).findFirst();
		
		if(filterItem.isPresent()) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteItemIfExist(Integer itemId, Order order) {
		
		if(!checkIteminOrder(itemId, order)) {
			return false;
		}
		
		//orderList.get((order.getOrderId()-1)).getItemList().remove((itemId-1));
		
		resetOrderList(itemId, order);
		log.info("item deleted -> success");
		return true;
	}
	
	public void resetOrderList(Integer id, Order order) {
		log.info("Delete item and reset the itemList....");
		//if(order.getItemList().isEmpty()) return;
		int i = 0;
		for(i=id-1; i<order.getItemList().size()-1; i++) {
			order.getItemList().set(i, order.getItemList().get(i+1));
			order.getItemList().get(i).setItemId(i+1);
		}
		
		order.getItemList().remove(i);
		
		orderList.set(order.getOrderId()-1, order);
		
		System.out.println(orderList.get(0));
	}

	
}
