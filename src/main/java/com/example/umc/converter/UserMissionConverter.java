package com.example.umc.converter;

import com.example.umc.Entity.UserMission;
import com.example.umc.dto.PageResponseDTO;
import com.example.umc.dto.UserMissionStatusDTO;
import org.springframework.data.domain.Page;

public class UserMissionConverter {

    private UserMissionConverter() {}

    public static UserMissionStatusDTO toStatus(UserMission userMission) {
        return UserMissionStatusDTO.builder()
                .userMissionId(userMission.getId())
                .userName(userMission.getUser().getName())
                .missionDetails(userMission.getMission().getDetails())
                .deadline(userMission.getMission().getDeadline())
                .point(userMission.getMission().getPoint())
                .storeName(userMission.getMission().getStore().getName())
                .isSuccess(userMission.getIsSuccess())
                .build();
    }

    public static PageResponseDTO<UserMissionStatusDTO> toPageResponse(Page<UserMission> missionPage) {
        return PageResponseDTO.<UserMissionStatusDTO>builder()
                .contents(missionPage.getContent().stream().map(UserMissionConverter::toStatus).toList())
                .page(missionPage.getNumber() + 1)
                .size(missionPage.getSize())
                .totalElements(missionPage.getTotalElements())
                .totalPages(missionPage.getTotalPages())
                .last(missionPage.isLast())
                .build();
    }
}

