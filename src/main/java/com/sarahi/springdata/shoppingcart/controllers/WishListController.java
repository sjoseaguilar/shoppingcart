package com.sarahi.springdata.shoppingcart.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sarahi.springdata.shoppingcart.model.Product;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.model.WishList;
import com.sarahi.springdata.shoppingcart.repos.ProductRepository;
import com.sarahi.springdata.shoppingcart.repos.UserRepository;
import com.sarahi.springdata.shoppingcart.repos.WishListRepository;


@RestController
@RequestMapping("/api")
public class WishListController {
	
	@Autowired
	private WishListRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	


	//add products to a wish list, each user should be able to see its own wish list.
	@RequestMapping(value = "/WishList",method = RequestMethod.POST)
	public ResponseEntity<Object> newProduct(@RequestBody WishListDTO dto) {
		WishList wish = new WishList();
		//System.out.println(dto.getUserId());
		wish.setUser(userRepository.findById(dto.getUserId()));
		Set<Product> products = new HashSet<>();
		for(long productId : dto.getProductsId()) {
			products.add(productRepository.findById(productId));
			
			//wish.addProduct(productRepository.findById(productId));
		}
		wish.setProducts(products);
		WishList savedWish = repository.save(wish);
		return ResponseEntity.ok(savedWish);
	}
	
	//method to delete a product from the wish list.
	//Aqui es put o delete?
	@RequestMapping(value = "/{USER_ID}/WishList/DeleteProduct/{PRODUCT_ID}",method = RequestMethod.PUT)
	public void deleteProduct(@PathVariable(value = "USER_ID") long userId, @PathVariable(value = "PRODUCT_ID") long productId) {
		User user = userRepository.findById(userId);
		WishList wish = user.getWishes();
		
		//productRepository = wish.getProducts();
		
		
	}
	
	//method to delete whole wish list.
	@RequestMapping(value = "/{USER_ID}/WishList/DeleteWishList",method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable(value = "USER_ID") long userId) {
		User user = userRepository.findById(userId);
		WishList wish = user.getWishes();
		repository.delete(wish);		
			
	}
	
	
	
}
