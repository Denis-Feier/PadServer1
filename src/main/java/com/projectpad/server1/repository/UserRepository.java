package com.projectpad.server1.repository;

import com.projectpad.server1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String username);
}
