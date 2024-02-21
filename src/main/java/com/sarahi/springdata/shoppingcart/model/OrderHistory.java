package com.sarahi.springdata.shoppingcart.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ORDER_HISTORY")
public class OrderHistory {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ORDER_ID", columnDefinition="numeric", precision=10, scale=0)
	private long orderId;
	@Column(name = "ORDER_DATE")
	@Temporal(TemporalType.DATE)
	private java.util.Date orderDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private Product product;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}



	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
