package com.sharp.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {
	
	private Integer itemId;
	
	private String itemName;
	
	private String itemDescription;
	
	private String quantity;
	
	private String price;

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	

}
