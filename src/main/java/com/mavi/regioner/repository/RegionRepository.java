package com.mavi.regioner.repository;

import com.mavi.regioner.model.Region;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {
    boolean existsRegionByCode(String code);

    Optional<Region> findRegionByName(String name);
}
