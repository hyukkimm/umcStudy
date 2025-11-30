package com.example.umc.Controller;

import com.example.umc.Service.MissionService;
import com.example.umc.common.annotation.ValidPage;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.apiPayload.code.GeneralSuccessCode;
import com.example.umc.dto.MissionRequestDTO;
import com.example.umc.dto.MissionResponseDTO;
import com.example.umc.dto.MissionSummaryDTO;
import com.example.umc.dto.PageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "미션 생성", description = "가게에 미션을 추가합니다.")
    public ResponseEntity<MissionResponseDTO> addMission(@RequestBody MissionRequestDTO dto) {
        MissionResponseDTO response = missionService.addMission(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stores/{storeId}")
    @Operation(summary = "가게 미션 목록", description = "특정 가게의 미션을 한 페이지에 10개씩 조회합니다.")
    public ResponseEntity<ApiResponse<PageResponseDTO<MissionSummaryDTO>>> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage @Parameter(description = "1 이상의 페이지 번호") @RequestParam int page
    ) {
        PageResponseDTO<MissionSummaryDTO> response = missionService.getMissionsByStore(storeId, page);
        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
}
