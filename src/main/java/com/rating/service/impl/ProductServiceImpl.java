package com.rating.service.impl;

import com.rating.dtos.ProductResponse;
import com.rating.models.Product;
import com.rating.models.Userr;
import com.rating.repositories.ProductRepo;
import com.rating.repositories.UserRepo;
import com.rating.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private UserServiceImpl userServiceImpl;


    ProductServiceImpl(UserServiceImpl userServiceImpl, ProductRepo productRepo){
        this.userServiceImpl = userServiceImpl;
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<?> addProduct(ProductResponse productData) {

        Product product = new Product();
        product.setProductId(productData.getProductId());
        product.setProductName(productData.getProductName());

        productRepo.save(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getProduct(String productId) {
        Product product = checkAndFetchProductAvailability(productId);
        if(product == null){
            return new ResponseEntity<>("Product not available!", HttpStatus.OK);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepo.findAll();
        if(products.isEmpty()){
            return new ResponseEntity<>("Products not available!", HttpStatus.OK);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> rateProduct(String productId, String userId, Integer rating) {

        Product product = checkAndFetchProductAvailability(productId);
        Userr user = userServiceImpl.checkAndFetchUserAvailability(userId);

        if(product == null || user == null){
            return new ResponseEntity<>("Product or User not available!", HttpStatus.OK);
        }
        if(rating <= 0 || rating >5){
            return new ResponseEntity<>("Rating can only be in the rang of 1-5", HttpStatus.OK);
        }
        product.rateProductByUser(user.getUserId(), rating);
        productRepo.save(product);
        return new ResponseEntity<>("Product rated", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> unrateProduct(String productId, String userId) {
        Product product = checkAndFetchProductAvailability(productId);
        Userr user = userServiceImpl.checkAndFetchUserAvailability(userId);

        if(product == null || user == null){
            return new ResponseEntity<>("Product or User not available!", HttpStatus.OK);
        }

        product.unrateProductByUser(Long.valueOf(userId));
        productRepo.save(product);
        return new ResponseEntity<>("Product Unrated!", HttpStatus.OK);
    }

    public Product checkAndFetchProductAvailability(String productId){
        Optional<Product> product = productRepo.findById(Long.valueOf(productId));
        return product.orElse(null);
    }

}
