package com.yofeely.integration.service;

import com.yofeely.integration.dtos.magento.MagentoAuthenticationRequest;
import com.yofeely.integration.dtos.magento.MagentoProductResponse;
import com.yofeely.integration.dtos.magento.MagentoUpdateQtyRequest;
import com.yofeely.integration.dtos.magento.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MagentoIntegrationService {
    final String baseUrl = "https://yofeely.com/index.php/rest/V1";
    final String authenticationUrl = "/integration/admin/token";
    final String productsUrl = "/products/";
    private String accessToken = "";

    @Autowired
    RestTemplate restTemplate;

    @Value("${yofeely.username}")
    private String username;

    @Value("${yofeely.password}")
    private String password;

    public MagentoProductResponse getProductBySku(String sku) {
        String url = baseUrl + productsUrl + sku;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<MagentoProductResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, MagentoProductResponse.class);
        return response.getBody();
    }

    public String updateStockQtyBySku(String sku, int qty) {
        try {
            if (accessToken.equals("")) accessToken = getAuthorizationToken();
            MagentoProductResponse product = getProductBySku(sku);
            String url = baseUrl + productsUrl + sku + "/stockItems/" + product.getId();
            MagentoUpdateQtyRequest request = MagentoUpdateQtyRequest.builder()
                    .stockItem(StockItem.builder()
                            .qty(qty)
                            .build())
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<MagentoUpdateQtyRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to update";
        }
    }

    private String  getAuthorizationToken() {
        String url = baseUrl + authenticationUrl;
        MagentoAuthenticationRequest authentication = MagentoAuthenticationRequest.builder()
                .username(username)
                .password(password)
                .build();
        HttpEntity<MagentoAuthenticationRequest> entity = new HttpEntity<>(authentication);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody().substring(1, response.getBody().length()-1);
    }
}
