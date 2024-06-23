package com.niq.personalized_data_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.niq.personalized_data_api.models.Product;
import com.niq.personalized_data_api.models.Shopper;
import com.niq.personalized_data_api.models.ShopperPersonalizedRequest;
import com.niq.personalized_data_api.repository.ProductRepository;
import com.niq.personalized_data_api.repository.ShopperRepository;
import com.niq.personalized_data_api.utils.AppUtils;

@Service
public class PersonalizedDataService {

	private ProductRepository productRepo;

	private ShopperRepository shopperRepo;

	public PersonalizedDataService(ProductRepository productRepo, ShopperRepository shopperRepo) {
		this.productRepo = productRepo;
		this.shopperRepo = shopperRepo;
	}

	@Cacheable(value = "shopperCache", key = "#shopperId")
	public List<Product> getProducts(String shopperId, String category, String brand, int limit) {

		limit = limit > 100 ? 100 : limit;

		return productRepo.findTheProducts(shopperId, category, brand,
				PageRequest.of(0, limit));
	}

	@CacheEvict(value = "shopperCache", key = "#shopperPersonalizedRequest.shopperId")
	public boolean ingestShopperPersonalizedData(ShopperPersonalizedRequest shopperPersonalizedRequest) {

		AppUtils.validateIngestShopperPersonalizedData(shopperPersonalizedRequest);

		Optional<Shopper> optShopper = shopperRepo.findById(shopperPersonalizedRequest.getShopperId());

		Shopper shopper = null;

		List<Product> filteredProd = shopperPersonalizedRequest.getShelf().parallelStream().filter(prod -> (null != prod
				&& StringUtils.isNotBlank(prod.getProductId()) && StringUtils.isNotBlank(prod.getRelevancyScore())))
				.collect(Collectors.toList());
		if (optShopper.isPresent()) {
			shopper = optShopper.get();
		} else {
			shopper = new Shopper();
			shopper.setShopperId(shopperPersonalizedRequest.getShopperId());
		}

		Map<String, Product> dbProductsMap = shopper.getProducts().parallelStream()
				.collect(Collectors.toMap(Product::getProductId, product -> product));

		List<Product> newProducts = new ArrayList<>();

		filteredProd.parallelStream().forEach(product -> {
			if (dbProductsMap.containsKey(product.getProductId())) {
				dbProductsMap.get(product.getProductId()).setRelevancyScore(product.getRelevancyScore());
			} else {
				Product prod = this.productRepo.findById(product.getProductId()).orElse(new Product());
				prod.setProductId(product.getProductId());
				prod.setRelevancyScore(product.getRelevancyScore());
				newProducts.add(prod);
			}
		});
		shopper.setProducts(new ArrayList<>(dbProductsMap.values()));
		if (!newProducts.isEmpty()) {
			newProducts.addAll(shopper.getProducts());
			shopper.setProducts(newProducts);
		}

		this.shopperRepo.save(shopper);

		return true;
	}

	@CacheEvict(value = "shopperCache", allEntries = true)
	public boolean ingestProductMetaData(Product productRequst) {

		AppUtils.validateIngestProductMetaData(productRequst);

		Optional<Product> optProd = this.productRepo.findById(productRequst.getProductId());

		Product product = null;
		if (optProd.isPresent()) {
			product = optProd.get();
			if (null != productRequst.getBrand()) {
				product.setBrand(productRequst.getBrand());
			}
			if (null != productRequst.getCategory()) {
				product.setCategory(productRequst.getCategory());
			}
		} else {
			product = productRequst;
		}

		this.productRepo.save(product);

		return true;
	}
}
