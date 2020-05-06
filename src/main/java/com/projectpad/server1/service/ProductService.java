package com.projectpad.server1.service;

import com.projectpad.server1.entity.PostData;
import com.projectpad.server1.entity.Product;
import com.projectpad.server1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByIds(List<Integer> pid) {
        return productRepository.findAllById(pid);
    }

    public double getTotalPrice(List<PostData> postData) {
        double totalPrice = 0;
        List<Integer> pids = postData.stream().map(PostData::getPid).collect(Collectors.toList());
        List<Double> prices = getAllProductsByIds(pids).stream().map(Product::getPrice).collect(Collectors.toList());
        for (int i = 0; i < postData.size(); i++) {
            totalPrice = totalPrice + postData.get(i).getQuantity() * prices.get(i);
        }
        return totalPrice;
    }
}
