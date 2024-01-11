package me.minkh.app.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RestTemplateTest {

    @Value("${token}")
    String token;

    @Autowired
    RestTemplate restTemplate;

    @DisplayName("value 테스트")
    @Test
    void valueTest() {
        assertThat(token.length()).isEqualTo(634);
        assertThat(token.startsWith("eyJ")).isTrue();
    }

    @DisplayName("RestTemplate 등록 테스트")
    @Test
    void restTemplateNotNullTest() {
        assertThat(this.restTemplate).isNotNull();
    }

    @DisplayName("API 연결 테스트 - 존재하는 캐릭터")
    @Test
    void apiConnectExistCharacterTest() {
        // given
        ResponseEntity<String> exchange = getExchange("짱돌봇");

        // then
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isNotEqualTo("null");

        // TODO : 로스트아크 서버가 점검중일 때는 요청이 실패할 것 같은데, 그에 대한 처리를 해야 될 지 고민해 봐야 한다.
    }

    @DisplayName("API 연결 테스트 - 존재하지 않는 캐릭터")
    @Test
    void apiConnectNotExistCharacterTest() {
        // given
        ResponseEntity<String> exchange = getExchange("존재하지않는닉네임닉네임닉네임닉네임");

        // then
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isEqualTo("null");
    }

    private ResponseEntity<String> getExchange(String characterName) {
        // given
        String url = "https://developer-lostark.game.onstove.com/armories/characters/" + characterName + "/profiles";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // when
        return this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
