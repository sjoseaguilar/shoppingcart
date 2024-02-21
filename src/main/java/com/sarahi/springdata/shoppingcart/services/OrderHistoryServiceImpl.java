package com.sarahi.springdata.shoppingcart.services;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarahi.springdata.shoppingcart.controllers.OrderHistoryDTO;
import com.sarahi.springdata.shoppingcart.model.OrderHistory;
import com.sarahi.springdata.shoppingcart.model.Product;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.repos.OrderHistoryRepository;
import com.sarahi.springdata.shoppingcart.repos.ProductRepository;
import com.sarahi.springdata.shoppingcart.repos.UserRepository;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
	
	@Autowired
	private OrderHistoryRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	//Method to buy one or more products, when some products are bought by an user, 
	//the field TOTAL_PRODUCTS_INVENTORY should decrease by the number of bought products. 
	//This bought should be registered in ORDER_HISTORY table.
	public ResponseEntity<Object> buyProduct(OrderHistoryDTO dto){
		try {
			Optional<User> user = Optional.of(userRepository.findById(dto.getUser_id()));
			Date date = new Date();
			for (Map<String, Object> productMapped : dto.getProducts()) {			
				OrderHistory order = new OrderHistory();
				order.setUser(user.get());
				int productId = (int)productMapped.get("product_id");
				Optional<Product> productToBuy = Optional.of(productRepository.findById(productId));
				int totalInventory = productToBuy.get().getTotalProductsInventory();
				String productName = productToBuy.get().getName();
				if(totalInventory == 0) {
					String errorMsg = "There is no more inventory for the product: " + productName;
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
				}
				int productQuantity = (int)productMapped.get("quantity");
				int newInventory = (int) (totalInventory - productQuantity);
				if(newInventory < 0) {
					String errorMsg = "There is no enough inventory for the product: " + productName;
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
				}
				productToBuy.get().setTotalProductsInventory(newInventory);
				productRepository.save(productToBuy.get());
				order.setProduct(productToBuy.get());
				order.setOrderDate(date);
				orderRepository.save(order);		
		    }
			return ResponseEntity.ok(orderRepository.findByUser(user.get()));
		}
		catch(Exception e) {
			String errorMsg = "UserId or ProductId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
	}

}
