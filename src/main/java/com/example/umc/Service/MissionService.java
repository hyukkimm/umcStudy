package com.example.umc.Service;

import com.example.umc.Entity.Mission;
import com.example.umc.Entity.Store;
import com.example.umc.Repository.MissionRepository;
import com.example.umc.Repository.StoreRepository;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.MissionRequestDTO;
import com.example.umc.dto.MissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResponseDTO addMission(MissionRequestDTO dto) {
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Mission mission = new Mission();
        mission.setDetails(dto.details());
        mission.setDeadline(dto.deadline());
        mission.setPoint(dto.point());
        mission.setStore(store);

        missionRepository.save(mission);

        return new MissionResponseDTO(
                mission.getId(),
                mission.getDetails(),
                mission.getDeadline(),
                mission.getPoint(),
                store.getName()
        );
    }
}
