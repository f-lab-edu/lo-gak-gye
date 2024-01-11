package me.minkh.app.dto.lostark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileDto {

    @JsonProperty("CharacterImage")
    private String characterImage;

    @JsonProperty("ExpeditionLevel")
    private int expeditionLevel;

    @JsonProperty("PvpGradeName")
    private String pvpGradeName;

    @JsonProperty("TownLevel")
    private int townLevel;

    @JsonProperty("TownName")
    private String townName;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("GuildMemberGrade")
    private String guildMemberGrade;

    @JsonProperty("GuildName")
    private String guildName;

    @JsonProperty("UsingSkillPoint")
    private int usingSkillPoint;

    @JsonProperty("TotalSkillPoint")
    private int totalSkillPoint;

    @JsonProperty("Stats")
    private List<Stat> stats;

    @JsonProperty("Tendencies")
    private List<Tendency> tendencies;

    @JsonProperty("ServerName")
    private String serverName;

    @JsonProperty("CharacterName")
    private String characterName;

    @JsonProperty("CharacterLevel")
    private int characterLevel;

    @JsonProperty("CharacterClassName")
    private String characterClassName;

    @JsonProperty("ItemAvgLevel")
    private String itemAvgLevel;

    @JsonProperty("ItemMaxLevel")
    private String itemMaxLevel;

    @Getter
    static class Stat {
        @JsonProperty("Type")
        private String type;

        @JsonProperty("Value")
        private String value;

        @JsonProperty("Tooltip")
        private List<String> tooltip;
    }

    @Getter
    static class Tendency {
        @JsonProperty("Type")
        private String type;

        @JsonProperty("Point")
        private int point;

        @JsonProperty("MaxPoint")
        private int maxPoint;
    }
}


