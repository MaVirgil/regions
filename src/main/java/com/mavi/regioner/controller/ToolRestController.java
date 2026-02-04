package com.mavi.regioner.controller;

import com.mavi.regioner.model.Municipality;
import com.mavi.regioner.model.Region;
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
}
