package com.example.yofeely.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.ZonedDateTime;

@Getter
public class LindoAuthenticationResponse {
    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    @JsonProperty("expires_in")
    int expiresIn;

    @JsonProperty("token_type")
    String tokenType;

}
