package com.example.umc.Controller;

import com.example.umc.Service.UserMissionService;
import com.example.umc.dto.UserMissionRequestDTO;
import com.example.umc.dto.UserMissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-missions")
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PostMapping
    public ResponseEntity<UserMissionResponseDTO> challengeMission(@RequestBody UserMissionRequestDTO dto) {
        UserMissionResponseDTO response = userMissionService.challengeMission(dto);
        return ResponseEntity.ok(response);
    }
}

