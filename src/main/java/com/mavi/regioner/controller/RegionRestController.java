package com.mavi.regioner.controller;

import com.mavi.regioner.model.Region;
import com.mavi.regioner.repository.RegionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("region")
public class RegionRestController {

    private final RegionRepository regionRepository;

    public RegionRestController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @GetMapping
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    @DeleteMapping
    public ResponseEntity<List<Region>> deleteAllRegions() {
        List<Region> regions = regionRepository.findAll();
        regionRepository.deleteAll();
        return ResponseEntity.ok(regions);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Region> deleteRegion(@PathVariable String code) {
        Optional<Region> found = regionRepository.findById(code);

        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Region region = found.get();
        regionRepository.delete(region);
        return ResponseEntity.ok(region);
    }
}
