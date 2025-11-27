package com.example.umc.dto;

public record StoreRequestDTO(
        String name,
        String information,
        String address,
        Long locationId
) {}