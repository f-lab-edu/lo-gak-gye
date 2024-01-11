package me.minkh.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.exception.CharacterNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
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

        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String body = response.getBody();

        if (Objects.equals(body, "null")) {
            throw new CharacterNotFoundException(characterName + "에 해당하는 캐릭터가 없습니다.");
        }

        try {
            return this.objectMapper.readValue(body, ProfileDto.class);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
            return null;
        }
    }
}
