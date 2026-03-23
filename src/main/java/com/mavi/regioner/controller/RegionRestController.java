package com.mavi.regioner.controller;

import com.mavi.regioner.Exception.ResourceNotFoundException;
import com.mavi.regioner.model.Region;
import com.mavi.regioner.repository.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("region")
@CrossOrigin("*")
public class RegionRestController {

    private final RegionRepository regionRepository;

    public RegionRestController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @GetMapping
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Region> getRegionByName(@PathVariable String name) {

        Region region = regionRepository.findRegionByName(name).orElseThrow( () -> new ResourceNotFoundException("Resource not found!", name));

        return ResponseEntity.ok(region);
    }

    @PostMapping
    public ResponseEntity<Region> addRegion(@RequestBody Region region) {
        System.out.println(region);

        if (regionRepository.findById(region.getCode()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        var added = regionRepository.save(region);
        return ResponseEntity.ok(added);
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
