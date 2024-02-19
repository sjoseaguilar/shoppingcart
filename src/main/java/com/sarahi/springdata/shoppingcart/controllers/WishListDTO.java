package com.sarahi.springdata.shoppingcart.controllers;

public class WishListDTO {

	private long userId;
	private long[] productsId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long[] getProductsId() {
		return productsId;
	}

	public void setProductsId(long[] productsId) {
		this.productsId = productsId;
	}
	
	
	
}
