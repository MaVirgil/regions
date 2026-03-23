package com.mavi.regioner.controller;

import com.mavi.regioner.model.Municipality;
import com.mavi.regioner.model.Region;
import com.mavi.regioner.repository.RegionRepository;
import com.mavi.regioner.service.ApiServiceGetMunicipalities;
import com.mavi.regioner.service.ApiServiceGetRegions;
import com.mavi.regioner.service.ApiServiceGetRegionsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolRestController {

    @Autowired
    ApiServiceGetRegions apiServiceGetRegions;

    @Autowired
    ApiServiceGetMunicipalities apiServiceGetMunicipalities;
    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/initialize")
    public ResponseEntity<String> initialize() {
        try {
            apiServiceGetRegions.getRegions();
            apiServiceGetMunicipalities.getMunicipalities();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong on the server");
        }
        return ResponseEntity.ok("Data initialized");
    }

    @GetMapping("/test")
    public void test() {
        Region region1 = new Region();
        region1.setName("Hovedstaden");
        region1.setHref("https://hovedstaden.dk");
        region1.setCode("0101");

        Municipality municipality1 = new Municipality();
        municipality1.setName("København");
        municipality1.setHref("https://kobenhavn.dk");
        municipality1.setCode("1000");

        Municipality municipality2 = new Municipality();
        municipality2.setName("Valby");
        municipality2.setHref("https://valby.dk");
        municipality2.setCode("2000");

        region1.getMunicipalityList().add(municipality1);
        region1.getMunicipalityList().add(municipality2);

        regionRepository.save(region1);
    }
}
