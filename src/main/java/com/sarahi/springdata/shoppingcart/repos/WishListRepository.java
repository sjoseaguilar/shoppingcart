package com.sarahi.springdata.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahi.springdata.shoppingcart.model.User;
import com.sarahi.springdata.shoppingcart.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
	List<WishList> findByUser(User user);
}
