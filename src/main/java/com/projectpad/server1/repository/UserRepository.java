package com.projectpad.server1.repository;

import com.projectpad.server1.entity.User;
import com.projectpad.server1.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    User findByTokens(UserToken token);
}
