package com.sarahi.springdata.shoppingcart.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahi.springdata.shoppingcart.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

}
