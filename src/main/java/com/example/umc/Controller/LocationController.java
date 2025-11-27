package com.example.umc.Controller;

import com.example.umc.Entity.Location;
import com.example.umc.Service.LocationService;
import com.example.umc.dto.LocationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    // 지역 추가 API
    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody LocationRequestDTO dto) {
        Location savedLocation = locationService.addLocation(dto);
        return ResponseEntity.ok(savedLocation);
    }
}
