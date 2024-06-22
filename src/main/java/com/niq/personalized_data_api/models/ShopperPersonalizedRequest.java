package com.niq.personalized_data_api.models;

import java.util.List;

public class ShopperPersonalizedRequest {

	private String shopperId;
	private List<Product> shelf;

	public String getShopperId() {
		return shopperId;
	}

	public void setShopperId(String shopperId) {
		this.shopperId = shopperId;
	}

	public List<Product> getShelf() {
		return shelf;
	}

	public void setShelf(List<Product> self) {
		this.shelf = self;
	}


}
