package me.minkh.app.service;

import me.minkh.app.dto.lostark.ProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class LostArkApiServiceTest {

    @Autowired
    LostArkApiService lostArkApiService;

    @Test
    void getProfiles() {
        ProfileDto profileDto = this.lostArkApiService.getProfiles("짱돌봇");
        System.out.println(profileDto);
    }
}