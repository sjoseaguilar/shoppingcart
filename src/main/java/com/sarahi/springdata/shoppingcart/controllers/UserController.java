package com.sarahi.springdata.shoppingcart.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sarahi.springdata.shoppingcart.model.OrderHistory;
import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.repos.OrderHistoryRepository;
import com.sarahi.springdata.shoppingcart.repos.UserRepository;

@RestController
@RequestMapping("/api/Users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderHistoryRepository orderRepository;
	
	
	//Create a method to insert new users into table shoppingcart.USERS.
	//If the user already exists, then should notify that the user already exists. You should use email field to validate.
	//Here use JSON in POSTMAN
	@RequestMapping(value = "/New",method = RequestMethod.POST)
	public ResponseEntity<Object> newUser(@RequestBody User user) {  
		User existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser != null) {
			String existingEmail = "The user with email: " + user.getEmail() + " already exists, try with a diferent email";
			return ResponseEntity.ok(existingEmail);
		}
		userRepository.save(user);	
		return ResponseEntity.ok(user);
	}
	
	//method to update an existing user. The only fields to update should be: email, area_of_interest.
	@PutMapping("/Update/{USER_ID}")
	public ResponseEntity<Object> updateUserField(@PathVariable("USER_ID")long userId, @RequestBody Map<String, String> user) {
		try {
			Optional<User> userUpdate = Optional.of(userRepository.findById(userId));	
			String newEmail = user.get("email");
			String newArea = user.get("areaOfInterest");
			if(newEmail != null) {
				userUpdate.get().setEmail(newEmail);
			}
			if(newArea != null) {
				userUpdate.get().setAreaOfInterest(newArea);
			}
			return ResponseEntity.ok(userRepository.findById(userId));
			
		}
		catch(Exception e) {
			String errorMsg = "UserId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
		
	}
	
	//Create a method to delete an existing USER
	//Delete a user, when the user is deleted, then the history would be cleared.	
	@RequestMapping(value = "/Delete/{USER_ID}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("USER_ID")long userId) {
		try {
			Optional<User> user = Optional.of(userRepository.findById(userId));	
			
			List<OrderHistory> orders = orderRepository.findByUser(user.get());
			for (OrderHistory order : orders) {
				order.setProduct(null);
				orderRepository.save(order);
				orderRepository.delete(order);
			}
			
			userRepository.delete(user.get());
			return ResponseEntity.ok("User deleted");
		}
		catch(Exception e) {
			String errorMsg = "UserId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
			
	}

	//method to get a list of all existing users
	@GetMapping(value="/All")
	public List<User> getUsers(){
		return userRepository.findAll();	
	}
	
	//method to get an specific user, filtered by name.
	@RequestMapping(value="/ByName/{NAME}", method = RequestMethod.GET)
	public List<User> getUserByName(@PathVariable("NAME")String name) {
		return userRepository.findByName(name);
		
	}
	
	//method to get an specific user, filtered by email.
	@RequestMapping(value="/ByEmail/{EMAIL}", method = RequestMethod.GET)
	public User getUserByEmail(@PathVariable("EMAIL")String email) {
		return userRepository.findByEmail(email);
	}
		
}
