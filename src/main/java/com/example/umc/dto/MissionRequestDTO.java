package com.example.umc.dto;

import java.time.LocalDateTime;

public record MissionRequestDTO(
        String details,
        LocalDateTime deadline,
        int point,
        Long storeId
) {}