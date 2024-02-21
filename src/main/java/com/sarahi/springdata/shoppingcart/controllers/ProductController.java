package com.sarahi.springdata.shoppingcart.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sarahi.springdata.shoppingcart.model.Product;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.repos.ProductRepository;

@RestController
@RequestMapping("/api/Products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	

	//Create a method to insert new products  into table shoppingcart.PRODUCTS.
	//If the product already exists, then the total_products_inventory field should increase by 1.
	@RequestMapping(value = "/New",method = RequestMethod.POST)
	public ResponseEntity<Object> newProduct(@RequestBody Product product) {
		String productName = product.getName();
		productName = productName.substring(0,1).toUpperCase()+ productName.substring(1);
		Product existingProduct = repository.findByName(productName);
		if(existingProduct != null) {
			int actualInventory = existingProduct.getTotalProductsInventory() + 1;
			existingProduct.setTotalProductsInventory(actualInventory);
			String msg = "The product already exists, there are: " + actualInventory + " " + existingProduct.getName();
			return ResponseEntity.ok(msg);
		}
		product.setName(productName);
		if(product.getTotalProductsInventory() == 0)
		{
			product.setTotalProductsInventory(1);
		}
		if(product.getTotalProductsInventory() > 0) {
			product.setStatus(true);
		}
		
		repository.save(product);
		return ResponseEntity.ok(product);
	}
	
	//Create a method to update an existing product.
	//The only fields to update should  be: Price, image, description and total_products_inventory
	@PutMapping("/Update/{PRODUCT_ID}")
	public ResponseEntity<Object> updateProductField(@PathVariable("PRODUCT_ID")long productId, @RequestBody Map<String, Object> product) {
		try {
			Optional<Product> productUpdate = Optional.of(repository.findById(productId));		
		}
		catch(Exception e) {
			String errorMsg = "ProductId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
		
		Optional<Product> productUpdate = Optional.of(repository.findById(productId));	
				
		if(product.get("price") != null) {
			int newPrice = (int)product.get("price");
			productUpdate.get().setPrice(newPrice);
		}
		if(product.get("image") != null) {
			byte[] newImage = (byte[])product.get("image");
			productUpdate.get().setImage(newImage);
		}
		if(product.get("description") != null) {
			String newDesc = (String)product.get("description");
			productUpdate.get().setDescription(newDesc);
		}
		if(product.get("totalProductsInventory") != null) {
			int newTotal = (int)product.get("totalProductsInventory");
			productUpdate.get().setTotalProductsInventory(newTotal);
		}

		return ResponseEntity.ok(productUpdate.get());
	}
	
	
	//Create a method to delete an existing product.
	//Deletion of a product must be virtual, only column status should be false.
	@RequestMapping(value = "/Delete/{PRODUCT_ID}",method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("PRODUCT_ID")long productId) {
		Product product = repository.findById(productId);
		product.setStatus(false);
		repository.save(product);
	}
	
	
	//method to get a list of all existing products
		@GetMapping(value="/All")
		public List<Product> getProducts(){
			return repository.findAll();	
		}
		
	//method to get an specific product, filtered by name.
	@RequestMapping(value="/ByName/{NAME}", method = RequestMethod.GET)
	public Product getUserByName(@PathVariable("NAME")String name) {
		return repository.findByName(name);
	}
	
	//method to get a list of products, filtered by price
	@RequestMapping(value="/ByPrice/{PRICE}", method = RequestMethod.GET)
	public List<Product> getUserByEmail(@PathVariable("PRICE")int price) {
		return repository.findByPrice(price);
	}

}
