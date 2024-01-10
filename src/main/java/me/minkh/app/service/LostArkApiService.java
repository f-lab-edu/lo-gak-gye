package me.minkh.app.service;

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

    public LostArkApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProfiles(String characterName) {
        String url = "https://developer-lostark.game.onstove.com/armories/characters/" + characterName + "/profiles";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
