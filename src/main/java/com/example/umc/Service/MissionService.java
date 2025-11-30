package com.example.umc.Service;

import com.example.umc.Entity.Mission;
import com.example.umc.Entity.Store;
import com.example.umc.Repository.MissionRepository;
import com.example.umc.Repository.StoreRepository;
import com.example.umc.converter.MissionConverter;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.MissionRequestDTO;
import com.example.umc.dto.MissionResponseDTO;
import com.example.umc.dto.MissionSummaryDTO;
import com.example.umc.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResponseDTO addMission(MissionRequestDTO dto) {
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Mission mission = Mission.builder()
                .details(dto.details())
                .deadline(dto.deadline())
                .point(dto.point())
                .store(store)
                .build();

        missionRepository.save(mission);

        return new MissionResponseDTO(
                mission.getId(),
                mission.getDetails(),
                mission.getDeadline(),
                mission.getPoint(),
                store.getName()
        );
    }

    public PageResponseDTO<MissionSummaryDTO> getMissionsByStore(Long storeId, int page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Mission> missionPage = missionRepository.findByStore(store, pageable);

        return MissionConverter.toPageResponse(missionPage);
    }
}
