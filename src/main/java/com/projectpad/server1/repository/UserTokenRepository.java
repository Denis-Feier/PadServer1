package com.projectpad.server1.repository;

import com.projectpad.server1.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

    UserToken findByToken(String t);
}
