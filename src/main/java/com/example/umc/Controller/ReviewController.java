package com.example.umc.Controller;

import com.example.umc.Entity.Review;
import com.example.umc.Service.ReviewService;
import com.example.umc.Service.ReviewQueryService;
import com.example.umc.common.annotation.ValidPage;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.apiPayload.code.GeneralSuccessCode;
import com.example.umc.dto.ReviewRequestDTO;
import com.example.umc.dto.ReviewResponseDTO;
import com.example.umc.dto.PageResponseDTO;
import com.example.umc.dto.ReviewSummaryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "리뷰 검색", description = "유저의 리뷰를 조건에 맞춰 검색합니다.")
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
    @Operation(summary = "리뷰 작성", description = "가게에 대한 리뷰를 작성합니다.")
    public ResponseEntity<ReviewResponseDTO> addReview(@RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO response = reviewService.addReview(dto);
        return ResponseEntity.ok(response);
    }

    // 내가 작성한 리뷰 목록
    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록", description = "해당 사용자가 작성한 리뷰를 페이징하여 조회합니다. 한 페이지에 10개씩 반환됩니다.")
    public ResponseEntity<ApiResponse<PageResponseDTO<ReviewSummaryDTO>>> getMyReviews(
            @RequestParam Long userId,
            @ValidPage @Parameter(description = "1 이상의 페이지 번호") @RequestParam int page
    ) {
        PageResponseDTO<ReviewSummaryDTO> response = reviewService.getUserReviews(userId, page);
        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
}
