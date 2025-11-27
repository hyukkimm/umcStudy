package com.example.umc.Service;

import com.example.umc.Entity.QReview;
import com.example.umc.Entity.Review;
import com.example.umc.Repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService{
    private final ReviewRepository reviewRepository;
    public List<Review> searchReview(Long userId,String storeName,Float star){
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        //동적 쿼리 : 검색 조건
        if(storeName != null && !storeName.isEmpty()){
            builder.and(review.store.name.contains(storeName));
        }
        if(star != null){
            builder.and(review.star.goe(star));
        }
        builder.and(review.user.id.eq(userId));

        return reviewRepository.searchReview(builder);
    }
}
