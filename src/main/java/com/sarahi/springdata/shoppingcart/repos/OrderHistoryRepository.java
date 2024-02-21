package com.sarahi.springdata.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahi.springdata.shoppingcart.model.OrderHistory;
import com.sarahi.springdata.shoppingcart.model.User;


public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

	List<OrderHistory> findByUser(User user);
}
