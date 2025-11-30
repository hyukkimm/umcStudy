package com.example.umc.Repository;

import com.example.umc.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 메서드 이름 기반 조회 가능
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    // @Query 방식
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findUserById(@Param("userId") Long userId);
}
