package com.dev.picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalAuthorizationService {

    private static final String AUTHORIZATION_URL = "https://util.devi.tools/api/v2/authorize";

    @Autowired
    private RestTemplate restTemplate;

    public boolean isAuthorized() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(AUTHORIZATION_URL, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody().contains("authorization");
            }
            return false;
        }
        catch (Exception ex) {
            throw new RuntimeException("Authorization service unavailable", ex);
        }
    }

}
