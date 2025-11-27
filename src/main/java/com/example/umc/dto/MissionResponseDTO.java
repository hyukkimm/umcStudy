package com.example.umc.dto;

import java.time.LocalDateTime;

public record MissionResponseDTO(
        Long id,
        String details,
        LocalDateTime deadline,
        int point,
        String storeName
) {}