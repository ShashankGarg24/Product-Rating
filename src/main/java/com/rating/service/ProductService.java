package com.rating.service;

import com.rating.dtos.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<?> addProduct(ProductResponse productData);

    ResponseEntity<?> getProduct(String productId);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> rateProduct(String productId, String userId, Integer rate);

    ResponseEntity<?> unrateProduct(String productId, String userId);
}
