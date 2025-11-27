package com.example.umc.Service;

import com.example.umc.Entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDSL {

    List<Review> searchReview(Predicate predicate);
}
