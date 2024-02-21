package com.sarahi.springdata.shoppingcart.controllers;

import java.util.Map;

public class OrderHistoryDTO {
	
	private long user_id;
	private Map<String, Object>[] products;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public Map<String, Object>[] getProducts() {
		return products;
	}

	public void setProducts(Map<String, Object>[] products) {
		this.products = products;
	}
	

	
}
