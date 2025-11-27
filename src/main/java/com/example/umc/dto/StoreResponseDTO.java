package com.example.umc.dto;

public record StoreResponseDTO(
        Long id,
        String name,
        String information,
        String address,
        String locationName
) {}
