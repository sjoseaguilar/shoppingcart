package com.sarahi.springdata.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahi.springdata.shoppingcart.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findById(int product_id);
	Product findByName(String name);
	List<Product> findByPrice(int price);
}
