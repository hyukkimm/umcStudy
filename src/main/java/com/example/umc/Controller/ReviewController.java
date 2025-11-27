package com.example.umc.Controller;

import com.example.umc.Entity.Review;
import com.example.umc.Service.ReviewService;
import com.example.umc.Service.ReviewQueryService;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.apiPayload.code.GeneralSuccessCode;
import com.example.umc.dto.ReviewRequestDTO;
import com.example.umc.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    // 리뷰 검색 API
    @GetMapping
    public ResponseEntity<ApiResponse<List<Review>>> searchReview(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float star
    ) {
        List<Review> result = reviewQueryService.searchReview(userId, storeName, star);
        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, result));
    }

    // 가게 리뷰 추가 API
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> addReview(@RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO response = reviewService.addReview(dto);
        return ResponseEntity.ok(response);
    }
}
