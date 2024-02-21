package com.sarahi.springdata.shoppingcart.controllers;


public class WishListDTO {

	private long user_id;
	private long[] product_id;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_d(long user_id) {
		this.user_id = user_id;
	}
	public long[] getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long[] product_id) {
		this.product_id = product_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	
}
