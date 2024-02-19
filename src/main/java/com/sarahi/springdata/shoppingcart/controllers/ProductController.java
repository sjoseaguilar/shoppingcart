package com.sarahi.springdata.shoppingcart.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sarahi.springdata.shoppingcart.model.Product;
import com.sarahi.springdata.shoppingcart.repos.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	

	//Create a method to insert new products  into table shoppingcart.PRODUCTS.
	//If the product already exists, then the total_products_inventory field should increase by 1.
	@RequestMapping(value = "/NewProduct",method = RequestMethod.POST)
	public Product newProduct(@RequestBody Product product) {
		String productName = product.getName();
		productName = productName.substring(0,1).toUpperCase()+ productName.substring(1);
		Product existingProduct = repository.findByName(productName);
		if(existingProduct != null) {
			int actualInventory = existingProduct.getTotalProductsInventory() + 1;
			existingProduct.setTotalProductsInventory(actualInventory);
			System.out.println("The product already exists, there are: " + actualInventory + " " + existingProduct.getName());
			return repository.save(existingProduct);
		}
		product.setName(productName);
		if(product.getTotalProductsInventory() == 0)
		{
			product.setTotalProductsInventory(1);
		}
		if(product.getTotalProductsInventory() > 0) {
			product.setStatus(true);
		}
		
		return repository.save(product);
	}
	
	//Create a method to update an existing product.
	//The only fields to update should  be: Price, image, description and total_products_inventory
	@PutMapping("/UpdateProduct/{PRODUCT_ID}")
	public void updateProductField(@PathVariable("PRODUCT_ID")long product_id, @RequestBody Product product, @RequestParam(defaultValue = "0")int price, byte[] image, String description, @RequestParam(defaultValue = "0")int total_products_inventory) {
		Product productUpdate = repository.findById(product_id);
		if(productUpdate != null) {
			if(price != 0) {
				productUpdate.setPrice(price);
			}
			if(image != null) {
				productUpdate.setImage(image);
			}
			if(description != null) {
				productUpdate.setDescription(description);
			}
			if(total_products_inventory != 0) {
				productUpdate.setTotalProductsInventory(total_products_inventory);
			}
			if(image != null) {
				productUpdate.setImage(image);
			}
			repository.save(productUpdate);
		}
	}
	
	
	//Create a method to delete an existing product.
	//Deletion of a product must be virtual, only column status should be false.
	@RequestMapping(value = "/DeleteProduct/{PRODUCT_ID}",method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("PRODUCT_ID")long product_id) {
		Product product = repository.findById(product_id);
		product.setStatus(false);
		repository.save(product);
	}
	
	
	//method to get a list of all existing products
		@GetMapping(value="/Products")
		public List<Product> getProducts(){
			return repository.findAll();	
		}
		
	//method to get an specific product, filtered by name.
	@RequestMapping(value="/ProductByName/{NAME}", method = RequestMethod.GET)
	public Product getUserByName(@PathVariable("NAME")String name) {
		return repository.findByName(name);
	}
	
	//method to get a list of products, filtered by price
	@RequestMapping(value="/ProductsByPrice/{PRICE}", method = RequestMethod.GET)
	public List<Product> getUserByEmail(@PathVariable("PRICE")int price) {
		return repository.findByPrice(price);
	}

}
