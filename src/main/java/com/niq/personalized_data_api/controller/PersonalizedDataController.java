package com.niq.personalized_data_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niq.personalized_data_api.models.Product;
import com.niq.personalized_data_api.models.ShopperPersonalizedRequest;
import com.niq.personalized_data_api.services.PersonalizedDataService;
import com.niq.personalized_data_api.utils.AppUtils;

@RestController
@RequestMapping("/api/v1/")
public class PersonalizedDataController {

	@Autowired
	private PersonalizedDataService personalizedDataService;

	@GetMapping("/shoppers/{shopperId}/products")
	public ResponseEntity<AppUtils.Products> getShopperProducts(@PathVariable String shopperId,
			@RequestParam(required = false) String category, @RequestParam(required = false) String brand,
			@RequestParam(required = false, defaultValue = "10") int limit) {

		List<Product> products = personalizedDataService.getProducts(shopperId, category, brand, limit);

		return ResponseEntity.ok(AppUtils.setProducts(products));
	}

	@PostMapping("/shoppers")
	public ResponseEntity<AppUtils.Status> ingestShopperPersonalizedData(
			@RequestBody ShopperPersonalizedRequest shopperPersonalizedRequest) {
		personalizedDataService.ingestShopperPersonalizedData(shopperPersonalizedRequest);
		return ResponseEntity.ok(AppUtils.successStatus());
	}

	@PostMapping("/product-metadata")
	public ResponseEntity<AppUtils.Status> ingestProductMetaData(@RequestBody Product product) { 
		personalizedDataService.ingestProductMetaData(product);
		return ResponseEntity.ok(AppUtils.successStatus());
	}

}
