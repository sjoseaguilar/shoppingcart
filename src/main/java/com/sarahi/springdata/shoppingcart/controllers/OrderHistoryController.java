package com.sarahi.springdata.shoppingcart.controllers;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sarahi.springdata.shoppingcart.model.OrderHistory;
import com.sarahi.springdata.shoppingcart.model.Product;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.repos.OrderHistoryRepository;
import com.sarahi.springdata.shoppingcart.repos.ProductRepository;
import com.sarahi.springdata.shoppingcart.repos.UserRepository;
import com.sarahi.springdata.shoppingcart.services.OrderHistoryService;

@RestController
@RequestMapping("/api")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryService service;
	
	//Function to buy products
	@RequestMapping(value = "/BuyProduct",method = RequestMethod.POST)
	public ResponseEntity<Object> buyProduct(@RequestBody OrderHistoryDTO dto) {		
		return service.buyProduct(dto);		
	}

}
