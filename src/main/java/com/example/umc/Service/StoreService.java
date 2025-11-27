package com.example.umc.Service;

import com.example.umc.Entity.Location;
import com.example.umc.Entity.Store;
import com.example.umc.Repository.LocationRepository;
import com.example.umc.Repository.StoreRepository;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import com.example.umc.dto.StoreRequestDTO;
import com.example.umc.dto.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;

    public StoreResponseDTO addStore(StoreRequestDTO dto) {
        Location location = locationRepository.findById(dto.locationId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Store store = new Store();
        store.setName(dto.name());
        store.setInformation(dto.information());
        store.setAddress(dto.address());
        store.setLocation(location);

        storeRepository.save(store);

        return new StoreResponseDTO(
                store.getId(),
                store.getName(),
                store.getInformation(),
                store.getAddress(),
                location.getName()
        );
    }
}
