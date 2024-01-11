package me.minkh.app.dto.lostark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProfileDtoTest {

    String profileJson = """
            {
              "CharacterImage": "https://img.lostark.co.kr/armory/5/005a95236972e8f24ea120aa265c0b2ed9c6d24467c0567d81110b3c698db26d.png?v=20231108011008",
              "ExpeditionLevel": 266,
              "PvpGradeName": "9단",
              "TownLevel": 70,
              "TownName": "봇영지",
              "Title": "마수의 포효",
              "GuildMemberGrade": "일반 길드원",
              "GuildName": "해무리언덕",
              "UsingSkillPoint": 420,
              "TotalSkillPoint": 420,
              "Stats": [
                {
                  "Type": "치명",
                  "Value": "674",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 치명타 적중률이 <font color='#99ff99'>24.12%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>"
                  ]
                },
                {
                  "Type": "특화",
                  "Value": "60",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 마력 강화 및 마력 해방의 속성 피해 효율이 <font color='#99ff99'>18.45%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 신비한 마력 게이지 획득량이 <font color='#99ff99'>2.14%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 각성 스킬의 피해량이 <font color='#99ff99'>3.27%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>24</font>만큼 영구적으로 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>"
                  ]
                },
                {
                  "Type": "제압",
                  "Value": "70",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 피격이상 및 상태이상 대상에게 주는 피해량이 <font color='#99ff99'>4.29%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 무력화 대상에게 주는 피해량이 <font color='#99ff99'></font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>32</font>만큼 영구적으로 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>"
                  ]
                },
                {
                  "Type": "신속",
                  "Value": "1637",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 공격 속도가 <font color='#99ff99'>28.12%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 이동 속도가 <font color='#99ff99'>28.12%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 스킬 재사용 대기시간이 <font color='#99ff99'>35.15%</font> 감소합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>"
                  ]
                },
                {
                  "Type": "인내",
                  "Value": "66",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물리 방어력이 <font color='#99ff99'>5.39%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 마법 방어력이 <font color='#99ff99'>5.39%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 보호막 효과가 <font color='#99ff99'>1.68%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 생명력 회복 효과가 <font color='#99ff99'>2.36%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>"
                  ]
                },
                {
                  "Type": "숙련",
                  "Value": "66",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 상태이상 공격 지속시간이 <font color='#99ff99'>2.83%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 상태이상 피해 지속시간이 <font color='#99ff99'>2.36%</font> 감소합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 무력화 피해량이 <font color='#99ff99'>1.88%</font> 증가합니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 물약 및 원정대 레벨 보상 효과로 <font color='#99ff99'>28</font>만큼 영구적으로 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 카드 도감 누적 효과가 반영된 값으로 전투정보실에서는 별도 수치를 표기하지 않습니다.</textformat>"
                  ]
                },
                {
                  "Type": "최대 생명력",
                  "Value": "174725",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 캐릭터의 최대 생명력을 나타냅니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><textformat indent='-21' leftMargin='10'><font> </font> 체력으로 최대 생명력이 <font color='#99ff99'>126058</font> 증가되었습니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 생명 활성력으로 최대 생명력이 <font color='#99ff99'>38.60%</font> 증가되었습니다.</textformat>"
                  ]
                },
                {
                  "Type": "공격력",
                  "Value": "57394",
                  "Tooltip": [
                    "<textformat indent='-21' leftMargin='10'><font> </font> 적에게 주는 피해를 계산할 때 기준이 되는 값입니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 힘, 민첩, 지능과 무기 공격력을 기반으로 증가한 기본 공격력은 <font color='#99ff99'>55592</font> 입니다.</textformat>",
                    "<textformat indent='-21' leftMargin='10'><font> </font> 공격력 증감 효과로 공격력이 <font color='#99ff99'>1802</font> 증가되었습니다.</textformat>"
                  ]
                }
              ],
              "Tendencies": [
                {
                  "Type": "지성",
                  "Point": 613,
                  "MaxPoint": 1000
                },
                {
                  "Type": "담력",
                  "Point": 619,
                  "MaxPoint": 1000
                },
                {
                  "Type": "매력",
                  "Point": 526,
                  "MaxPoint": 1000
                },
                {
                  "Type": "친절",
                  "Point": 536,
                  "MaxPoint": 1000
                }
              ],
              "ServerName": "니나브",
              "CharacterName": "짱돌봇",
              "CharacterLevel": 60,
              "CharacterClassName": "소서리스",
              "ItemAvgLevel": "1,627.50",
              "ItemMaxLevel": "1,627.50"
            }
            """;

    String errorJson = """
            {
              "error": "json",
            }
            """;

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("ProfileDTO 변환 성공 테스트")
    @Test
    void profileDtoConvertSuccessTest() throws JsonProcessingException {
        // given
        ProfileDto profileDto = objectMapper.readValue(this.profileJson, ProfileDto.class);

        // when
        int length = profileDto.getStats().toArray().length;

        // then
        assertThat(length).isEqualTo(8);
    }

    @DisplayName("ProfileDTO 변환 실패 테스트")
    @Test
    void profileDtoConvertFailTest() {
        assertThatThrownBy(() -> objectMapper.readValue(this.errorJson, ProfileDto.class))
                .isInstanceOf(JsonProcessingException.class);
    }
}