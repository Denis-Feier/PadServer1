package com.projectpad.server1.controller;

import com.projectpad.server1.entity.Product;
import com.projectpad.server1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("product/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product with id: " + id + " not found"));
    }
}
