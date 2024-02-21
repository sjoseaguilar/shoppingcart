package com.sarahi.springdata.shoppingcart.services;

import org.springframework.http.ResponseEntity;

import com.sarahi.springdata.shoppingcart.controllers.OrderHistoryDTO;

public interface OrderHistoryService {

	public ResponseEntity<Object> buyProduct(OrderHistoryDTO dto);
}
