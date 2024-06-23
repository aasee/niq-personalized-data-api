package com.niq.personalized_data_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.niq.personalized_data_api.models.Product;
import com.niq.personalized_data_api.models.Shopper;
import com.niq.personalized_data_api.models.ShopperPersonalizedRequest;
import com.niq.personalized_data_api.repository.ProductRepository;
import com.niq.personalized_data_api.repository.ShopperRepository;

public class PersonalizedDataServiceTest {

    @Mock
    private ProductRepository productRepo;

    @Mock
    private ShopperRepository shopperRepo;

    @InjectMocks
    private PersonalizedDataService personalizedDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProducts() {
        List<Product> mockProducts = List.of(new Product());
        when(productRepo.findTheProducts(anyString(), anyString(), anyString(), any()))
                .thenReturn(mockProducts);

        List<Product> products = personalizedDataService.getProducts("shopper1", "category1", "brand1", 10);
        assertEquals(mockProducts, products);
    }

    @Test
    public void testIngestShopperPersonalizedData() {
        Shopper shopper = new Shopper();
        shopper.setShopperId("shopper1");

        when(shopperRepo.findById(anyString())).thenReturn(Optional.of(shopper));
        when(productRepo.findById(anyString())).thenReturn(Optional.of(new Product()));

        ShopperPersonalizedRequest request = new ShopperPersonalizedRequest();
        request.setShopperId("shopper1");
        request.setShelf(List.of(new Product()));

        boolean result = personalizedDataService.ingestShopperPersonalizedData(request);
        assertTrue(result);
        verify(shopperRepo, times(1)).save(any(Shopper.class));
    }

    @Test
    public void testIngestProductMetaData() {
        Product product = new Product();
        product.setProductId("product1");

        when(productRepo.findById(anyString())).thenReturn(Optional.of(product));

        boolean result = personalizedDataService.ingestProductMetaData(product);
        assertTrue(result);
        verify(productRepo, times(1)).save(any(Product.class));
    }
}
