package me.minkh.app.dto.lostark;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileDto {

    @JsonAlias("CharacterImage")
    private String characterImage;

    @JsonAlias("ExpeditionLevel")
    private int expeditionLevel;

    @JsonAlias("PvpGradeName")
    private String pvpGradeName;

    @JsonAlias("TownLevel")
    private int townLevel;

    @JsonAlias("TownName")
    private String townName;

    @JsonAlias("Title")
    private String title;

    @JsonAlias("GuildMemberGrade")
    private String guildMemberGrade;

    @JsonAlias("GuildName")
    private String guildName;

    @JsonAlias("UsingSkillPoint")
    private int usingSkillPoint;

    @JsonAlias("TotalSkillPoint")
    private int totalSkillPoint;

    @JsonAlias("Stats")
    private List<Stat> stats;

    @JsonAlias("Tendencies")
    private List<Tendency> tendencies;

    @JsonAlias("ServerName")
    private String serverName;

    @JsonAlias("CharacterName")
    private String characterName;

    @JsonAlias("CharacterLevel")
    private int characterLevel;

    @JsonAlias("CharacterClassName")
    private String characterClassName;

    @JsonAlias("ItemAvgLevel")
    private String itemAvgLevel;

    @JsonAlias("ItemMaxLevel")
    private String itemMaxLevel;

    @Getter
    static class Stat {
        @JsonAlias("Type")
        private String type;

        @JsonAlias("Value")
        private int value;

        @JsonAlias("Tooltip")
        private List<String> tooltip;
    }

    @Getter
    static class Tendency {
        @JsonAlias("Type")
        private String type;

        @JsonAlias("Point")
        private int point;

        @JsonAlias("MaxPoint")
        private int maxPoint;
    }
}


