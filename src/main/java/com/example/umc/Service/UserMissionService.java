package com.example.umc.Service;

import com.example.umc.Entity.Mission;
import com.example.umc.Entity.User;
import com.example.umc.Entity.UserMission;
import com.example.umc.Repository.MissionRepository;
import com.example.umc.Repository.UserMissionRepository;
import com.example.umc.Repository.UserRepository;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.UserMissionRequestDTO;
import com.example.umc.dto.UserMissionResponseDTO;
import lombok.RequiredArgsConstructor;
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

        UserMission userMission = new UserMission();
        userMission.setUser(user);
        userMission.setMission(mission);
        userMission.setIsSuccess(false);

        userMissionRepository.save(userMission);

        return new UserMissionResponseDTO(
                userMission.getId(),
                user.getName(),
                mission.getDetails(),
                userMission.getIsSuccess()
        );
    }
}

