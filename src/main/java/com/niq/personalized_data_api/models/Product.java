package com.niq.personalized_data_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
	
	@Id
	@Column(name = "product_id")
	private String productId;

	@Column
	private String category;

	@Column
	private String brand;

	@Column
	private String relevancyScore;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shopper_id")
	private Shopper shopper;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRelevancyScore() {
		return relevancyScore;
	}

	public void setRelevancyScore(String relevancyScore) {
		this.relevancyScore = relevancyScore;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}
}
