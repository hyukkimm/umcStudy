package com.example.umc.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserMissionStatusDTO {
    private final Long userMissionId;
    private final String userName;
    private final String missionDetails;
    private final LocalDateTime deadline;
    private final int point;
    private final String storeName;
    private final Boolean isSuccess;
}

