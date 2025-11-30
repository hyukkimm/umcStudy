package com.example.umc.Repository;

import com.example.umc.Entity.Review;
import com.example.umc.Service.query.ReviewQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDSL {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO review(user_id, store_id, star, content, created_at) VALUES (:userId, :storeId, :star, :content, :createdAt)", nativeQuery = true)
    void insertReview(
            @Param("userId") Long userId,
            @Param("storeId") Long storeId,
            @Param("star") Float star,
            @Param("content") String content,
            @Param("createdAt") LocalDateTime createdAt
    );
}
