package com.sharp.main.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
	
	private Integer orderId;
	
	private List<Item> itemList;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", itemList=" + itemList + "]";
	}
	
	
}
