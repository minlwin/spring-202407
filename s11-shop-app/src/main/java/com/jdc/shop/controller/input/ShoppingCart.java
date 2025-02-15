package com.jdc.shop.controller.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final Map<Integer, ShoppingCartItem> cart;
	
	public ShoppingCart() {
		cart = new LinkedHashMap<>();
	}
	
	public void addToCart(ShoppingCartItem item) {
		var oldItem = cart.get(item.getId());
		
		if(null == oldItem) {
			cart.put(item.getId(), item);
			oldItem = item;
		}
		
		oldItem.addOne();
	}
	
	public void removeOne(int id) {
		var oldItem = cart.get(id);
		
		if(null != oldItem) {
			oldItem.removeOne();
			if(oldItem.getQuantity() == 0) {
				cart.remove(id);
			}
		}
	}
	
	public void clear() {
		cart.clear();
	}
	
	public List<ShoppingCartItem> getItems() {
		return new ArrayList<>(cart.values());
	}

	public int getTotalCount() {
		return cart.values().stream().mapToInt(a -> a.getQuantity()).sum();
	}
	
	public int getTotalAmount() {
		return cart.values().stream().mapToInt(a -> a.getTotal()).sum();
	}
	
}
