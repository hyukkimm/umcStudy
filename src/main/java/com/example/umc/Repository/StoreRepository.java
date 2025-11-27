package com.example.umc.Repository;

import com.example.umc.Entity.Location;
import com.example.umc.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> { }
