package com.example.umc.Controller;

import com.example.umc.Entity.Store;
import com.example.umc.Service.StoreService;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.apiPayload.code.GeneralSuccessCode;
import com.example.umc.dto.StoreRequestDTO;
import com.example.umc.dto.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponseDTO> addStore(@RequestBody StoreRequestDTO dto) {
        StoreResponseDTO response = storeService.addStore(dto);
        return ResponseEntity.ok(response);
    }
}

