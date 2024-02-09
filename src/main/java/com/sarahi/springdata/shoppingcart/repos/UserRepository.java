package com.sarahi.springdata.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahi.springdata.shoppingcart.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByName(String name);
	User findByEmail(String email);
	User findById(int user_id);

}
