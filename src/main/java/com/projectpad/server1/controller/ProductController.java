package com.projectpad.server1.controller;

import com.projectpad.server1.entity.Product;
import com.projectpad.server1.service.ProductService;
import com.projectpad.server1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("product/all")
    public List<Product> getAllProducts(@RequestHeader("Authorization") String token) {
        String t = token.substring(7);
        userService.unauthorizedUser(t);
        logger.info("All products request");
        return productService.getAllProducts();
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
        String t = token.substring(7);
        userService.unauthorizedUser(t);
        logger.info("Get product by id: {}", id);
        return productService.getProductById(id)
                .orElseThrow(() -> {
                    logger.error("Product with id: {} not found",
                            id);
                        return new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Product with id: %d not found", id)); });
    }
}
