package com.sarahi.springdata.shoppingcart.model;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCTS")
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="PRODUCT_ID", columnDefinition="numeric", precision=10, scale=0)
	private long productId;
	@Column(name="NAME")
	private String name;
	@Column(name="PRICE")
	private int price;
	@Column(name="IMAGE")
	private byte[] image;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="TOTAL_PRODUCTS_INVENTORY")
	private int totalProductsInventory;
	@Column(name="STATUS")
	private boolean status;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalProductsInventory() {
		return totalProductsInventory;
	}

	public void setTotalProductsInventory(int totalProductsInventory) {
		this.totalProductsInventory = totalProductsInventory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
