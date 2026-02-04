package com.mavi.regioner.service;

import com.mavi.regioner.model.Region;
import com.mavi.regioner.repository.RegionRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ApiServiceGetRegionsImpl implements ApiServiceGetRegions{

    private final RestClient restClient;

    private final RegionRepository regionRepository;

    public ApiServiceGetRegionsImpl(RestClient restClient, RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
        this.restClient = restClient;
    }

    @Override
    public List<Region> getRegions() {
        String REGION_URL = "https://api.dataforsyningen.dk/regioner";

        List<Region> regions = restClient.get()
                .uri(REGION_URL)
                .header("Accept-Encoding", "identity")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Region>>() {});

        saveRegions(regions);
        return regions;
    }

    private void saveRegions(List<Region> regions) {
        regionRepository.saveAll(regions);
    }
}
