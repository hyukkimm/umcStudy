package com.example.umc.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}

