package com.projectpad.server1.repository;

import com.projectpad.server1.entity.OrderItemDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDBRepository extends JpaRepository<OrderItemDB, Integer> {
}
