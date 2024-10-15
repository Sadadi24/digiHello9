package com.digi.digiHello9.services;

import com.digi.digiHello9.model.Departement;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class DepartementApiService {
    private static final String API_URL = "https://geo.api.gouv.fr/departements/";

    public Departement getDepartementInfo(String codeDepartement) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + codeDepartement + "?fields=nom,code,codeRegion";
        return restTemplate.getForObject(url, Departement.class);
    }
}
