package me.minkh.app.dto.lostark;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

@Getter
public class LostArkEquipmentResponse {

    @JsonAlias("Type")
    private String type;

    @JsonAlias("Name")
    private String name;

    @JsonAlias("Icon")
    private String icon;

    @JsonAlias("Grade")
    private String grade;

    @JsonAlias("Tooltip")
    private String tooltip;
}