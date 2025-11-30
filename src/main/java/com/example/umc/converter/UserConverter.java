package com.example.umc.converter;

import com.example.umc.Entity.User;
import com.example.umc.common.auth.enums.Role;
import com.example.umc.dto.UserReqDTO;
import com.example.umc.dto.UserResDTO;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(
            User user
    ){
        return UserResDTO.JoinDTO.builder()
                .memberId(user.getId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(
            UserReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(String.valueOf(dto.birth()))
                .gender(dto.gender())
                .build();
    }
}
