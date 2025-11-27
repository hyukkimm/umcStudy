package com.example.umc.dto;

public record UserMissionResponseDTO(
        Long id,
        String userName,
        String missionDetails,
        Boolean isSuccess
) {}
