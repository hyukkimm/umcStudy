package com.example.umc.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String details;

    private LocalDateTime deadline;

    @Column(nullable = false)
    private int point;

    // N:1 미션 → 가게
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // 1:N 미션 → 유저미션
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> userMissions = new ArrayList<>();
}
