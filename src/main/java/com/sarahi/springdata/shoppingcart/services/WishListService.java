package com.sarahi.springdata.shoppingcart.services;

import org.springframework.http.ResponseEntity;

import com.sarahi.springdata.shoppingcart.controllers.WishListDTO;

public interface WishListService {

	public ResponseEntity<Object> addProduct(WishListDTO dto);
	
	public ResponseEntity<Object> deleteProduct(WishListDTO dto);
	
	public ResponseEntity<Object> deleteWishList(WishListDTO dto);
	
}
