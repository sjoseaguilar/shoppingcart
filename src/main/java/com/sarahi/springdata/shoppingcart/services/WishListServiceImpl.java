package com.sarahi.springdata.shoppingcart.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarahi.springdata.shoppingcart.controllers.WishListDTO;
import com.sarahi.springdata.shoppingcart.model.Product;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.model.WishList;
import com.sarahi.springdata.shoppingcart.repos.ProductRepository;
import com.sarahi.springdata.shoppingcart.repos.UserRepository;
import com.sarahi.springdata.shoppingcart.repos.WishListRepository;

@Service
public class WishListServiceImpl implements WishListService{
	
	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	//Add products to a wish list, each user should be able to see its own wish list.
	@Override
	public ResponseEntity<Object> addProduct(WishListDTO dto) {
		try {
			Optional<User> user = Optional.of(userRepository.findById(dto.getUser_id()));
			for(long productId : dto.getProduct_id()) {
				WishList wish = new WishList();
				wish.setUser(user.get());
				Optional<Product> product = Optional.of(productRepository.findById(productId));
				wish.setProduct(product.get());
				wishListRepository.save(wish);
			}
			return ResponseEntity.ok(wishListRepository.findByUser(user.get()));
		}
		
		catch(Exception e) {
			String errorMsg = "UserId or ProductId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
	}
	
	
	//Function to delete a product from the wish list.
	@Override
	public ResponseEntity<Object> deleteProduct(WishListDTO dto){
		try {
			Optional<User> user = Optional.of(userRepository.findById(dto.getUser_id()));
			List<WishList> userWishLists = wishListRepository.findByUser(user.get());
			//Deletes only one instantiate of the product selected
			for(long productIdToDelete : dto.getProduct_id()) {
				for(WishList productInWishList: userWishLists) {
					Optional<Product> product = Optional.of(productInWishList.getProduct());
					long productId = product.get().getProductId();
					if(productId == productIdToDelete) {
						wishListRepository.delete(productInWishList);
						break;
					}
				}
			}
			return ResponseEntity.ok(wishListRepository.findByUser(user.get()));
		}
		catch(Exception e) {
			String errorMsg = "UserId or ProductId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}	
	}
	
	
	//Function to delete whole wish list
	@Override
	public ResponseEntity<Object> deleteWishList(WishListDTO dto){
		try {
			Optional<User> user = Optional.of(userRepository.findById(dto.getUser_id()));
			List<WishList> userWishLists = wishListRepository.findByUser(user.get());	
			for(WishList wishes: userWishLists) {
				wishListRepository.delete(wishes);
			}
			return ResponseEntity.ok(user);
		}
		catch(Exception e) {
			String errorMsg = "UserId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
	}
	

}
