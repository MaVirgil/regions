package com.mavi.regioner.service;

import com.mavi.regioner.model.Municipality;
import com.mavi.regioner.repository.MunicipalityRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ApiServiceGetMunicipalitiesImpl implements ApiServiceGetMunicipalities{

    private final RestClient restClient;

    private final MunicipalityRepository municipalityRepository;

    public ApiServiceGetMunicipalitiesImpl(RestClient restClient, MunicipalityRepository municipalityRepository) {
        this.restClient = restClient;
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public List<Municipality> getMunicipalities() {
        String REGION_URL = "https://api.dataforsyningen.dk/kommuner";

        List<Municipality> municipalities = restClient.get()
                .uri(REGION_URL)
                .header("Accept-Encoding", "identity")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Municipality>>() {});

        saveMunicipalities(municipalities);
        return municipalities;
    }

    private void saveMunicipalities(List<Municipality> municipalities) {
        municipalityRepository.saveAll(municipalities);
    }
}
