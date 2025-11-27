package com.example.umc.dto;

public record ReviewRequestDTO(
        Long userId,
        Long storeId,
        String content,
        Float star
) {}
