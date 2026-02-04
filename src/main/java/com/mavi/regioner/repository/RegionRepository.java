package com.mavi.regioner.repository;

import com.mavi.regioner.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}
