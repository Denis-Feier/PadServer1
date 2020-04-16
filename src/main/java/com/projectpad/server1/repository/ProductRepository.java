package com.projectpad.server1.repository;

import com.projectpad.server1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
