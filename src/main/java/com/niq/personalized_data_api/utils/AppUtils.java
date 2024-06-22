package com.niq.personalized_data_api.utils;

import java.util.List;

import com.niq.personalized_data_api.models.Product;

public final class AppUtils {
	private AppUtils() {
		
	}
	
	public static record Status(String status) {};
	
	public static record Products(List<Product> products) {};
	
	public static Status successStatus() {
		return new Status("Success");
	}
	
	public static Products setProducts(List<Product> products) {
		return new Products(products);
	}

}
