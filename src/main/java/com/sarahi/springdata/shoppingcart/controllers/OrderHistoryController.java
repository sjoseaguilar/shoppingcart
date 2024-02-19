package com.sarahi.springdata.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sarahi.springdata.shoppingcart.model.OrderHistory;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.repos.OrderHistoryRepository;
import com.sarahi.springdata.shoppingcart.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	//Add a method to buy one or more products, when some products are bought 
	//by an user, the field TOTAL_PRODUCTS_INVENTORY should decrease by the number
	//of bought products. This bought should be registered in ORDER_HISTORY table.
	@RequestMapping(value = "/{USER_ID}/BuyProduct",method = RequestMethod.POST)
	public OrderHistory newOrder(@PathVariable(value = "USER_ID") long userId, @RequestBody OrderHistory order) {
		User user = userRepository.findById(userId);
		order.setUser(user);
		return repository.save(order);
	}
}
