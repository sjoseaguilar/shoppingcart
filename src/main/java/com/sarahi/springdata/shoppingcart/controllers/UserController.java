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
import com.sarahi.springdata.shoppingcart.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	
	//Create a method to insert new users into table shoppingcart.USERS.
	//If the user already exists, then should notify that the user already exists. You should use email field to validate.
	//Here use JSON in POSTMAN
	@RequestMapping(value = "/NewUser",method = RequestMethod.POST)
	public User newUser(@RequestBody User user) {  
		User existingUser = repository.findByEmail(user.getEmail());
		if(existingUser != null) {
			System.out.println("The user with email: " + user.getEmail() + " already exists");
			return null;
		}
		return repository.save(user);	
	}
	
	//method to update an existing user. The only fields to update should be: email, area_of_interest.
	@PutMapping("/UpdateUser/{USER_ID}")
	public ResponseEntity<Object> updateUserField(@PathVariable("USER_ID")long userId, @RequestBody Map<String, String> user) {
		try {
			Optional<User> userUpdate = Optional.of(repository.findById(userId));	
			String newEmail = user.get("email");
			String newArea = user.get("areaOfInterest");
			if(newEmail != null) {
				userUpdate.get().setEmail(newEmail);
			}
			if(newArea != null) {
				userUpdate.get().setAreaOfInterest(newArea);
			}
			return ResponseEntity.ok(repository.findById(userId));
			
		}
		catch(Exception e) {
			String errorMsg = "UserId incorrect";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
		}
		
	}
	
	//Create a method to delete an existing USER
	//Delete a user, when the user is deleted, then the history would be cleared.	
	@RequestMapping(value = "/DeleteUser/{USER_ID}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("USER_ID")long userId) {
		User user = repository.findById(userId);
		repository.delete(user);	
	}

	
	//method to get a list of all existing users
	@GetMapping(value="/Users")
	public List<User> getUsers(){
		return repository.findAll();	
	}
	
	//method to get an specific user, filtered by name.
	@RequestMapping(value="/UserByName/{NAME}", method = RequestMethod.GET)
	public List<User> getUserByName(@PathVariable("NAME")String name) {
		return repository.findByName(name);
	}
	
	//method to get an specific user, filtered by email.
	@RequestMapping(value="/UserByEmail/{EMAIL}", method = RequestMethod.GET)
	public User getUserByEmail(@PathVariable("EMAIL")String email) {
		return repository.findByEmail(email);
	}
		
}
