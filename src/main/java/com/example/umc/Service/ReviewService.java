package com.example.umc.Service;

import com.example.umc.Entity.Review;
import com.example.umc.Entity.Store;
import com.example.umc.Entity.User;
import com.example.umc.Repository.ReviewRepository;
import com.example.umc.Repository.StoreRepository;
import com.example.umc.Repository.UserRepository;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.ReviewRequestDTO;
import com.example.umc.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
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

        Review review = new Review();
        review.setUser(user);
        review.setStore(store);
        review.setContent(dto.content());
        review.setStar(dto.star());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);

        return new ReviewResponseDTO(
                review.getId(),
                review.getContent(),
                review.getStar(),
                user.getName(),
                store.getName()
        );
    }
}
