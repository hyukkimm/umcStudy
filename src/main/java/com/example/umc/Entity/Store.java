package com.example.umc.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 50)
    private String information;

    @Column(length = 30)
    private String address;

    // N:1 관계 - 가게 → 지역
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // 1:N 가게 → 미션
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    // 1:N 가게 → 리뷰
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
