package com.example.umc.converter;

import com.example.umc.Entity.Review;
import com.example.umc.dto.PageResponseDTO;
import com.example.umc.dto.ReviewSummaryDTO;
import org.springframework.data.domain.Page;

public class ReviewConverter {

    private ReviewConverter() {}

    public static ReviewSummaryDTO toSummary(Review review) {
        return ReviewSummaryDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .storeName(review.getStore().getName())
                .userName(review.getUser().getName())
                .build();
    }

    public static PageResponseDTO<ReviewSummaryDTO> toPageResponse(Page<Review> reviewPage) {
        return PageResponseDTO.<ReviewSummaryDTO>builder()
                .contents(reviewPage.getContent().stream().map(ReviewConverter::toSummary).toList())
                .page(reviewPage.getNumber() + 1)
                .size(reviewPage.getSize())
                .totalElements(reviewPage.getTotalElements())
                .totalPages(reviewPage.getTotalPages())
                .last(reviewPage.isLast())
                .build();
    }
}

