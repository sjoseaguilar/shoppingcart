package com.sarahi.springdata.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sarahi.springdata.shoppingcart.services.WishListService;


@RestController
@RequestMapping("/api/WishList")
public class WishListController {
	
	@Autowired
	private WishListService service;
	

	//Function to add products to a wish list
	@RequestMapping(value = "/AddProduct",method = RequestMethod.POST)
	public ResponseEntity<Object> newProduct(@RequestBody WishListDTO dto) {	
		return service.addProduct(dto);
	}
		
	
	//Function to delete a product from the wish list.
	@RequestMapping(value = "/DeleteProduct",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@RequestBody WishListDTO dto) {		
		return service.deleteProduct(dto);		
	}
	
	
	//Function to delete whole wish list.
	@RequestMapping(value = "/DeleteWishList",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteWishProduct(@RequestBody WishListDTO dto) {
		return service.deleteWishList(dto);	
		
	}
	
}
