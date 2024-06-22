package com.niq.personalized_data_api.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Shoppers")
public class Shopper {

	@Id
	@Column(name = "shopper_id", nullable = false, unique = true)
	private String shopperId;

	@OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products = new ArrayList<>();

	public String getShopperId() {
		return shopperId;
	}

	public void setShopperId(String shopperId) {
		this.shopperId = shopperId;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
        this.products.clear();
        if (products != null) {
            products.forEach(product -> product.setShopper(this));
            this.products.addAll(products);
        }
    }

//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
}
