package com.yofeely.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegrationService {

    @Autowired
    MagentoIntegrationService magentoIntegrationService;

    @Autowired
    LindoIntegrationService lindoIntegrationService;

    public String updateLindoStock() {
        return null;
    }
}
