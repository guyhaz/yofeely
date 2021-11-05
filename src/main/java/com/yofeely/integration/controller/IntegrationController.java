package com.yofeely.integration.controller;

import com.yofeely.integration.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/integrations")
public class IntegrationController {

    @Autowired
    IntegrationService integrationService;

    @PostMapping("/updateLindoStock")
    public ResponseEntity<Object> updateLindoStock() {
        try {
            return ResponseEntity.ok(integrationService.updateLindoStock());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }
}
