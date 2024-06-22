package com.niq.personalized_data_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niq.personalized_data_api.models.Shopper;

public interface ShopperRepository extends JpaRepository<Shopper, String> {
}