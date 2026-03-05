package com.tap.servlet;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer,CartItem> cart;
	public Cart() {
		this.cart=new HashMap<Integer, CartItem>();
	}
	public void addCartItem(CartItem item) {
		int itemId = item.getId();
		if(cart.containsKey(itemId)) {
			CartItem existingItem = cart.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
		}
		else {
			cart.put(itemId, item);
		}
	}
	public void updateCartItem(int itemId,int quantity) {
		if(cart.containsKey(itemId)) {
			if(quantity<=0) {
				cart.remove(itemId);
			}
			else {
				cart.get(itemId).setQuantity(quantity);
			}
		}
	}
	public void removeCartItem(int itemId) {
		cart.remove(itemId);
	}
	public Map<Integer,CartItem> getItems(){
		return cart;
	}
	public void clear() {
		cart.clear();
	}
	public double getTotalPrice() {
		double totalPrice = 0.0;
	    for (CartItem item : cart.values()) {
	        totalPrice += item.getPrice() * item.getQuantity();
	    }
	    return totalPrice;
	}
	}
