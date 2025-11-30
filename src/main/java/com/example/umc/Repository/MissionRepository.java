package com.example.umc.Repository;

import com.example.umc.Entity.Mission;
import com.example.umc.Entity.Location;
import com.example.umc.Entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 현재 선택된 지역에서 도전 가능한 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.store.location = :location")
    Page<Mission> findMissionsByLocation(@Param("location") Location location, Pageable pageable);

    Page<Mission> findByStore(Store store, Pageable pageable);
}
