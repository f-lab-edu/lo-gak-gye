package me.minkh.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.minkh.app.dto.lostark.ProfileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LostArkApiService {

    @Value("${token}")
    private String token;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public LostArkApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ProfileDto getProfiles(String characterName) {
        String url = "https://developer-lostark.game.onstove.com/armories/characters/" + characterName + "/profiles";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String body = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

        try {
            return this.objectMapper.readValue(body, ProfileDto.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("JSON 처리에 실패하였습니다.", e);
        }
    }
}
