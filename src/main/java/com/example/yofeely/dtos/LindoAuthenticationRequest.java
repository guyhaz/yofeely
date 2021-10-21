package com.example.yofeely.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LindoAuthenticationRequest {
    @JsonProperty("client_id")
    String clientId;

    @JsonProperty("client_secret")
    String clientSecret;
}
