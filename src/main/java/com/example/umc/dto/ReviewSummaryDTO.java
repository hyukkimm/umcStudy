package com.example.umc.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewSummaryDTO {
    private final Long id;
    private final String content;
    private final Float star;
    private final String storeName;
    private final String userName;
}

