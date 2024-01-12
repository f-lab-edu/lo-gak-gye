package me.minkh.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.minkh.app.dto.lostark.ProfileDto;
import me.minkh.app.exception.CharacterNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LostArkApiServiceMockTest {

    @Mock
    RestTemplate restTemplate;

    LostArkApiService lostArkApiService;

    @BeforeEach
    void beforeEach() {
        lostArkApiService = new LostArkApiService(restTemplate, new ObjectMapper());
    }

    @DisplayName("프로필 조회에 성공하는 테스트")
    @Test
    void getProfiles() {
        // given
        String characterName = "성공하는_아이디";
        String body = "{\"CharacterImage\":\"https://img.lostark.co.kr/armory/3/4e981415a43bbcec46957c00118d2759a20aff8a22aee5c817cb3b1d3b4f3280.png?v=20240110164151\",\"ExpeditionLevel\":291,\"PvpGradeName\":\"2단\",\"TownLevel\":70,\"TownName\":\"우체통\",\"Title\":null,\"GuildMemberGrade\":\"일반 길드원\",\"GuildName\":\"단합안되는쓰레기길드\",\"UsingSkillPoint\":420,\"TotalSkillPoint\":420,\"Stats\":[{\"Type\":\"치명\",\"Value\":\"559\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 치명타 적중률이 <font color='#99ff99'>20.00%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\"]},{\"Type\":\"특화\",\"Value\":\"1849\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 타격 시 듀얼 게이지 회복량이 <font color='#99ff99'>92.58%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 집중 스킬의 피해량이 <font color='#99ff99'>111.09%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 각성 스킬의 피해량이 <font color='#99ff99'>101.04%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\"]},{\"Type\":\"제압\",\"Value\":\"70\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 피격이상 및 상태이상 대상에게 주는 피해량이 <font color='#99ff99'>4.29%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 무력화 대상에게 주는 피해량이 <font color='#99ff99'></font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>32</font>만큼 영구적으로 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\"]},{\"Type\":\"신속\",\"Value\":\"166\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 공격 속도가 <font color='#99ff99'>2.85%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 이동 속도가 <font color='#99ff99'>2.85%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 스킬 재사용 대기시간이 <font color='#99ff99'>3.56%</font> 감소합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\"]},{\"Type\":\"인내\",\"Value\":\"66\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 물리 방어력이 <font color='#99ff99'>5.39%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 마법 방어력이 <font color='#99ff99'>5.39%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 보호막 효과가 <font color='#99ff99'>1.68%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 생명력 회복 효과가 <font color='#99ff99'>2.36%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\"]},{\"Type\":\"숙련\",\"Value\":\"66\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 상태이상 공격 지속시간이 <font color='#99ff99'>2.83%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 상태이상 피해 지속시간이 <font color='#99ff99'>2.36%</font> 감소합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 무력화 피해량이 <font color='#99ff99'>1.88%</font> 증가합니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>\"]},{\"Type\":\"최대 생명력\",\"Value\":\"250657\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 캐릭터의 최대 생명력을 나타냅니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><textformat indent='-21' leftMargin='10'><font> </font> 체력으로 최대 생명력이 <font color='#99ff99'>150748</font> 증가되었습니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 생명 활성력으로 최대 생명력이 <font color='#99ff99'>50.00%</font> 증가되었습니다.</textformat>\"]},{\"Type\":\"공격력\",\"Value\":\"74047\",\"Tooltip\":[\"<textformat indent='-21' leftMargin='10'><font> </font> 적에게 주는 피해를 계산할 때 기준이 되는 값입니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 힘, 민첩, 지능과 무기 공격력을 기반으로 증가한 기본 공격력은 <font color='#99ff99'>70832</font> 입니다.</textformat>\",\"<textformat indent='-21' leftMargin='10'><font> </font> 공격력 증감 효과로 공격력이 <font color='#99ff99'>3215</font> 증가되었습니다.</textformat>\"]}],\"Tendencies\":[{\"Type\":\"지성\",\"Point\":622,\"MaxPoint\":1000},{\"Type\":\"담력\",\"Point\":633,\"MaxPoint\":1000},{\"Type\":\"매력\",\"Point\":608,\"MaxPoint\":1000},{\"Type\":\"친절\",\"Point\":541,\"MaxPoint\":1000}],\"ServerName\":\"루페온\",\"CharacterName\":\"편지\",\"CharacterLevel\":60,\"CharacterClassName\":\"창술사\",\"ItemAvgLevel\":\"1,655.00\",\"ItemMaxLevel\":\"1,655.00\"}\n";

        // when
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenReturn(ResponseEntity.ok(body));
        ProfileDto profileDto = this.lostArkApiService.getProfiles(characterName);

        // then
        assertThat(profileDto.getStats().size()).isEqualTo(8);
        assertThat(profileDto.getServerName()).isEqualTo("루페온");
    }

    @DisplayName("프로필 조회에 실패하는 테스트")
    @Test
    void getProfilesFail() {
        // given
        String characterName = "실패하는_없는_아이디";

        // when & then
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenReturn(ResponseEntity.ok("null"));

        assertThatThrownBy(() -> this.lostArkApiService.getProfiles(characterName))
                .isInstanceOf(CharacterNotFoundException.class)
                .hasMessage(characterName + "에 해당하는 캐릭터가 없습니다.");
    }

    @DisplayName("인증 토큰이 올바르지 않을 때, 실패하는 테스트")
    @Test
    void getProfiles401() {
        // given
        String characterName = "성공하는_아이디";

        // when & then
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThatThrownBy(() -> this.lostArkApiService.getProfiles(characterName))
                .isInstanceOf(HttpClientErrorException.class);
    }
}