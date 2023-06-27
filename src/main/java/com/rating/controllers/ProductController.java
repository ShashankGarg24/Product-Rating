package com.rating.controllers;

import com.rating.dtos.ProductResponse;
import com.rating.dtos.UserResponse;
import com.rating.service.ProductService;
import com.rating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductResponse productData){
        try{
            return productService.addProduct(productData);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to add product!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String productId){
        try{
            return productService.getProduct(productId);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to fetch product for id " + productId + "!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(){
        try{
            return productService.getAllProducts();
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to fetch products!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product/{id}/rate")
    public ResponseEntity<?> rateProduct(@PathVariable("id") String productId, @RequestParam("userId") String userId, @RequestParam("rating") Integer rate){
        try{
            return productService.rateProduct(productId, userId, rate);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to rate product!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product/{id}/unrate")
    public ResponseEntity<?> unrateProduct(@PathVariable("id") String productId, @RequestParam("userId") String userId){
        try{
            return productService.unrateProduct(productId, userId);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to unrate product!", HttpStatus.BAD_REQUEST);
        }
    }
}
