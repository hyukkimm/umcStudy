package com.example.umc.converter;

import com.example.umc.Entity.Mission;
import com.example.umc.dto.MissionSummaryDTO;
import com.example.umc.dto.PageResponseDTO;
import org.springframework.data.domain.Page;

public class MissionConverter {

    private MissionConverter() {}

    public static MissionSummaryDTO toSummary(Mission mission) {
        return MissionSummaryDTO.builder()
                .id(mission.getId())
                .details(mission.getDetails())
                .deadline(mission.getDeadline())
                .point(mission.getPoint())
                .storeName(mission.getStore().getName())
                .build();
    }

    public static PageResponseDTO<MissionSummaryDTO> toPageResponse(Page<Mission> missionPage) {
        return PageResponseDTO.<MissionSummaryDTO>builder()
                .contents(missionPage.getContent().stream().map(MissionConverter::toSummary).toList())
                .page(missionPage.getNumber() + 1)
                .size(missionPage.getSize())
                .totalElements(missionPage.getTotalElements())
                .totalPages(missionPage.getTotalPages())
                .last(missionPage.isLast())
                .build();
    }
}

