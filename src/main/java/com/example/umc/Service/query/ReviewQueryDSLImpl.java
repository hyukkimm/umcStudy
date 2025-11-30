package com.example.umc.Service.query;

import com.example.umc.Entity.QReview;
import com.example.umc.Entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDSLImpl implements ReviewQueryDSL {

    private final EntityManager em;

    // 검색 API
    @Override
    public List<Review> searchReview(Predicate predicate){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
