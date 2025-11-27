package com.example.umc.Repository;

import com.example.umc.Entity.User;
import com.example.umc.Entity.UserMission;
import com.example.umc.Entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 유저의 진행 중인 미션 조회
    Page<UserMission> findByUserAndIsSuccessFalse(User user, Pageable pageable);

    // 유저의 완료된 미션 조회
    Page<UserMission> findByUserAndIsSuccessTrue(User user, Pageable pageable);

    // 특정 유저의 모든 미션 조회
    Page<UserMission> findByUser(User user, Pageable pageable);
}
