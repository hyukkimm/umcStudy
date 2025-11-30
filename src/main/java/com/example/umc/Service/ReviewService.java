package com.example.umc.Service;

import com.example.umc.Entity.Review;
import com.example.umc.Entity.Store;
import com.example.umc.Entity.User;
import com.example.umc.Repository.ReviewRepository;
import com.example.umc.Repository.StoreRepository;
import com.example.umc.Repository.UserRepository;
import com.example.umc.converter.ReviewConverter;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.ReviewRequestDTO;
import com.example.umc.dto.PageResponseDTO;
import com.example.umc.dto.ReviewResponseDTO;
import com.example.umc.dto.ReviewSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewResponseDTO addReview(ReviewRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(dto.content())
                .star(dto.star())
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        return new ReviewResponseDTO(
                review.getId(),
                review.getContent(),
                review.getStar(),
                user.getName(),
                store.getName()
        );
    }

    public PageResponseDTO<ReviewSummaryDTO> getUserReviews(Long userId, int page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Review> reviewPage = reviewRepository.findByUser(user, pageable);

        return ReviewConverter.toPageResponse(reviewPage);
    }
}
