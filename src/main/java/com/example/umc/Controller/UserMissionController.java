package com.example.umc.Controller;

import com.example.umc.Service.UserMissionService;
import com.example.umc.dto.UserMissionRequestDTO;
import com.example.umc.dto.UserMissionResponseDTO;
import com.example.umc.dto.PageResponseDTO;
import com.example.umc.dto.UserMissionStatusDTO;
import com.example.umc.common.annotation.ValidPage;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-missions")
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PostMapping
    @Operation(summary = "미션 도전", description = "사용자가 특정 미션에 도전합니다.")
    public ResponseEntity<UserMissionResponseDTO> challengeMission(@RequestBody UserMissionRequestDTO dto) {
        UserMissionResponseDTO response = userMissionService.challengeMission(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}/ongoing")
    @Operation(summary = "진행 중인 미션 목록", description = "사용자의 진행 중인 미션을 10개씩 페이징하여 조회합니다.")
    public ResponseEntity<ApiResponse<PageResponseDTO<UserMissionStatusDTO>>> getOngoingMissions(
            @PathVariable Long userId,
            @ValidPage @Parameter(description = "1 이상의 페이지 번호") @RequestParam int page
    ) {
        PageResponseDTO<UserMissionStatusDTO> response = userMissionService.getOngoingMissions(userId, page);
        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }

    @PatchMapping("/{userMissionId}/complete")
    @Operation(summary = "미션 완료", description = "진행 중인 미션을 완료로 전환하고 상태를 반환합니다.")
    public ResponseEntity<ApiResponse<UserMissionStatusDTO>> completeMission(
            @PathVariable Long userMissionId
    ) {
        UserMissionStatusDTO response = userMissionService.completeMission(userMissionId);
        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
}

