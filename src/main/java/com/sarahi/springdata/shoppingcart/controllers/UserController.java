package com.sarahi.springdata.shoppingcart.controllers;

import java.util.List;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void updateUserField(@PathVariable("USER_ID")long user_id, @RequestBody User user, String email, String area_of_interest) {
		User userUpdate = repository.findById(user_id);
		if(userUpdate != null) {
			if(email != null) {
				userUpdate.setEmail(email);
			}
			if(area_of_interest != null) {
				userUpdate.setAreaOfInterest(area_of_interest);
			}
			repository.save(userUpdate);
		}
	}
	
	//Create a method to delete an existing USER
	//Delete a user, when the user is deleted, then the history would be cleared.	
	@RequestMapping(value = "/DeleteUser/{USER_ID}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("USER_ID")long user_id) {
		User user = repository.findById(user_id);
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
