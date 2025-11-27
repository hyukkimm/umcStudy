package com.example.umc.Controller;

import com.example.umc.Entity.Mission;
import com.example.umc.Entity.UserMission;
import com.example.umc.Service.MissionService;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.apiPayload.code.GeneralSuccessCode;
import com.example.umc.dto.MissionRequestDTO;
import com.example.umc.dto.MissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;
    //미션 추가 API
    @PostMapping
    public ResponseEntity<MissionResponseDTO> addMission(@RequestBody MissionRequestDTO dto) {
        MissionResponseDTO response = missionService.addMission(dto);
        return ResponseEntity.ok(response);
    }
}
