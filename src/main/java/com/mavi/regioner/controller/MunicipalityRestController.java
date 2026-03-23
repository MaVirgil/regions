package com.mavi.regioner.controller;

import com.mavi.regioner.model.Municipality;
import com.mavi.regioner.repository.MunicipalityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/municipality")
@CrossOrigin("*")
public class MunicipalityRestController {

    private final MunicipalityRepository municipalityRepository;

    public MunicipalityRestController(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    @GetMapping
    public List<Municipality> getMunicipalities() {
        return municipalityRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Municipality> addMunicipality(@RequestBody Municipality municipality) {
        System.out.println(municipality);

        if (municipalityRepository.findById(municipality.getCode()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        var added = municipalityRepository.save(municipality);
        return ResponseEntity.ok(added);
    }

    @DeleteMapping()
    public ResponseEntity<List<Municipality>> deleteAllMunicipalities() {
        List<Municipality> municipalities = municipalityRepository.findAll();
        municipalityRepository.deleteAll();
        return ResponseEntity.ok(municipalities);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Municipality> deleteMunicipality(@PathVariable String code) {
        Optional<Municipality> found = municipalityRepository.findById(code);

        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Municipality municipality = found.get();

        municipalityRepository.delete(municipality);
        return ResponseEntity.ok(municipality);
    }
}
