package me.minkh.app.dto.lostark;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.List;

@Getter
public class EngravingsDto {

    @JsonAlias("Engravings")
    private List<Engraving> engravings;

    @JsonAlias("Effects")
    private List<Effect> effects;

    @Getter
    public static class Effect {

        @JsonAlias("Icon")
        private String icon;

        @JsonAlias("Name")
        private String name;

        @JsonAlias("Description")
        private String description;
    }

    @Getter
    public static class Engraving {

        @JsonAlias("Slot")
        private int slot;

        @JsonAlias("Name")
        private String name;

        @JsonAlias("Icon")
        private String icon;

        @JsonAlias("Tooltip")
        private String tooltip;
    }
}

