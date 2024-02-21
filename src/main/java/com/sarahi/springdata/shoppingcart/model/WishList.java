package com.sarahi.springdata.shoppingcart.model;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="WISHLIST")
public class WishList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="WISH_ID", columnDefinition="numeric", precision=10, scale=0)
	private long wishId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="user_id")
	//private User user;

	
	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="product_id")
	private Product product;

	public long getWishId() {
		return wishId;
	}

	public void setWishId(long wishId) {
		this.wishId = wishId;
	}

	public User getUser() {
		return user;
	}
	
	public long getUserId() {
		return user.getUserId();
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
