package com.example.umc.Service;

import com.example.umc.Entity.Location;
import com.example.umc.Repository.LocationRepository;
import com.example.umc.dto.LocationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location addLocation(LocationRequestDTO dto) {
        Location location = new Location();
        location.setName(dto.name());
        return locationRepository.save(location);
    }
}
