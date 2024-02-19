package com.sarahi.springdata.shoppingcart.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="USER_ID", columnDefinition="numeric", precision=10, scale=0)
	private long userId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "BIO")
	private String bio;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "AREA_OF_INTEREST")
	private String areaOfInterest;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<OrderHistory> orders;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private WishList wishes;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaOfInterest() {
		return areaOfInterest;
	}

	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}

	public Set<OrderHistory> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderHistory> orders) {
		this.orders = orders;
	}

	public WishList getWishes() {
		return wishes;
	}

	public void setWishes(WishList wishes) {
		this.wishes = wishes;
	}
	
	public void addOrder(OrderHistory order) {
		this.orders.add(order);
		order.setUser(this);
	}
	


	
}
