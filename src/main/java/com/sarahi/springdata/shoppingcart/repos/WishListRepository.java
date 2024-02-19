package com.sarahi.springdata.shoppingcart.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahi.springdata.shoppingcart.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}
