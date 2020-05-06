package com.projectpad.server1.repository;

import com.projectpad.server1.entity.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDBRepository extends JpaRepository<OrderDB, Integer> {

    List<OrderDB> getOrderDBSByUid(int uid);
}
