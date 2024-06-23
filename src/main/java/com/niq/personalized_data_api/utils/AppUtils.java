package com.niq.personalized_data_api.utils;

import java.util.List;

import com.niq.personalized_data_api.error.handling.AppException;
import com.niq.personalized_data_api.models.Product;
import com.niq.personalized_data_api.models.ShopperPersonalizedRequest;

public final class AppUtils {
	private AppUtils() {

	}

	public static record Status(String status) {
	};

	public static record Products(List<Product> products) {
	};

	public static Status successStatus() {
		return new Status("Success");
	}

	public static Products setProducts(List<Product> products) {
		return new Products(products);
	}

	public static void validateIngestShopperPersonalizedData(ShopperPersonalizedRequest shopperPersonalizedRequest) {

		if (null == shopperPersonalizedRequest || null == shopperPersonalizedRequest.getShopperId()
				|| null == shopperPersonalizedRequest.getShelf() || shopperPersonalizedRequest.getShelf().size() == 0) {
			throw new AppException("Invalid request data!");
		}
	}

	public static void validateIngestProductMetaData(Product productRequst) {
		if (null == productRequst || null == productRequst.getProductId()) {
			throw new AppException("Invalid request data!");
		}
	}
}
