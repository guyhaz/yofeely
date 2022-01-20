package com.yofeely.integration.service;

import com.yofeely.integration.dtos.Lindo.LindoAuthenticationRequest;
import com.yofeely.integration.dtos.Lindo.LindoAuthenticationResponse;
import com.yofeely.integration.dtos.Lindo.LindoProductsRequest;
import com.yofeely.integration.dtos.Lindo.LindoProductsResponse;
import com.yofeely.integration.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LindoIntegrationService {
    final String baseUrl = "https://api.lindo.co.il/v1";
    final String authenticationUrl = "/token/generate";
    final String productsUrl = "/products/metadata";

    @Autowired
    RestTemplate restTemplate;

    @Value("${lindo.clientId}")
    private String clientId;

    @Value("${lindo.clientSecret}")
    private String clientSecret;

    public List<Product> filterProducts(LindoProductsRequest filter) {
        return sendProductsRequest(filter).stream()
                .map(Product::from)
                .collect(Collectors.toList());
    }

    public Product getProductQuantityBySku(String sku) {
        LindoProductsRequest request = LindoProductsRequest.builder()
                .sku(sku)
                .build();
        return Product.from(sendProductsRequest(request).stream()
                .findAny()
                .orElse(new LindoProductsResponse()));
    }

    public Collection<LindoProductsResponse> sendProductsRequest(LindoProductsRequest request) {
        String url = baseUrl + productsUrl;

        String accessToken = authenticate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setConnection("keep-alive");

        HttpEntity<LindoProductsRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<HashMap<String, LindoProductsResponse>>
                response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<HashMap<String, LindoProductsResponse>>() {});

        return response.getBody().values();
    }

    private String authenticate() {
        String url = baseUrl + authenticationUrl;
        LindoAuthenticationRequest authentication = LindoAuthenticationRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
        HttpEntity<LindoAuthenticationRequest> entity = new HttpEntity<>(authentication);
        ResponseEntity<LindoAuthenticationResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, LindoAuthenticationResponse.class);

        return response.getBody().getAccessToken();
    }

}
