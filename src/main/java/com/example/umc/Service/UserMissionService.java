package com.example.umc.Service;

import com.example.umc.Entity.Mission;
import com.example.umc.Entity.User;
import com.example.umc.Entity.UserMission;
import com.example.umc.Repository.MissionRepository;
import com.example.umc.Repository.UserMissionRepository;
import com.example.umc.Repository.UserRepository;
import com.example.umc.converter.UserMissionConverter;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.UserMissionRequestDTO;
import com.example.umc.dto.UserMissionResponseDTO;
import com.example.umc.dto.PageResponseDTO;
import com.example.umc.dto.UserMissionStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    public UserMissionResponseDTO challengeMission(UserMissionRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .isSuccess(false)
                .build();

        userMissionRepository.save(userMission);

        return new UserMissionResponseDTO(
                userMission.getId(),
                user.getName(),
                mission.getDetails(),
                userMission.getIsSuccess()
        );
    }

    public PageResponseDTO<UserMissionStatusDTO> getOngoingMissions(Long userId, int page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<UserMission> missionPage = userMissionRepository.findByUserAndIsSuccessFalse(user, pageable);

        return UserMissionConverter.toPageResponse(missionPage);
    }

    public UserMissionStatusDTO completeMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        userMission.setIsSuccess(true);
        userMissionRepository.save(userMission);

        return UserMissionConverter.toStatus(userMission);
    }
}

