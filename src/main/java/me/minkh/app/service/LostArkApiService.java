package me.minkh.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.minkh.app.dto.lostark.LostArkEngravingsResponse;
import me.minkh.app.dto.lostark.LostArkEquipmentResponse;
import me.minkh.app.dto.lostark.LostArkProfilesResponse;
import me.minkh.app.exception.CharacterNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Slf4j
@Service
public class LostArkApiService {

    @Value("${lost-ark-api-url}")
    private String lostArkApiUrl;

    private static final String BEARER = "Bearer ";

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public LostArkApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public LostArkProfilesResponse getProfiles(String characterName, String apiKey) {
        return callApi(characterName, "/profiles", apiKey, LostArkProfilesResponse.class);
    }

    public LostArkEngravingsResponse getEngravings(String characterName, String apiKey) {
        return callApi(characterName, "/engravings", apiKey, LostArkEngravingsResponse.class);
    }

    public LostArkEquipmentResponse[] getEquipment(String characterName, String apiKey) {
        return callApi(characterName, "/equipment", apiKey, LostArkEquipmentResponse[].class);
    }

    private <T> T callApi(String characterName, String path, String apikey, Class<T> clazz) {
        String url = getUrl(characterName, path);

        HttpEntity<String> httpEntity = getHttpEntity(apikey);

        String body = this.restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        if (Objects.equals(body, "null")) {
            throw new CharacterNotFoundException(characterName + "에 해당하는 캐릭터가 없습니다.");
        }

        try {
            return this.objectMapper.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
            return null;
        }
    }

    private HttpEntity<String> getHttpEntity(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, BEARER + apiKey);
        return new HttpEntity<>(headers);
    }

    private String getUrl(String characterName, String path) {
        return UriComponentsBuilder.fromHttpUrl(this.lostArkApiUrl)
                .path(characterName)
                .path(path)
                .build()
                .toUriString();
    }
}
