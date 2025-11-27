package com.example.umc.dto;

public record ReviewResponseDTO(
        Long id,
        String content,
        Float star,
        String userName,
        String storeName
) {}
