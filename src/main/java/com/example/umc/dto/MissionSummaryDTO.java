package com.example.umc.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MissionSummaryDTO {
    private final Long id;
    private final String details;
    private final LocalDateTime deadline;
    private final int point;
    private final String storeName;
}

