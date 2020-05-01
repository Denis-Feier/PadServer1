package com.projectpad.server1.repository;

import com.projectpad.server1.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

    UserToken findByToken(String t);

    @Transactional
    @Modifying
    void deleteByTid(int tid);

    boolean existsByToken(String token);
}
