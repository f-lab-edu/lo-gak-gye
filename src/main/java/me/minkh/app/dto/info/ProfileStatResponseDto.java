package me.minkh.app.dto.info;

import lombok.Getter;

@Getter
public class ProfileStatResponseDto {

    private final String type;

    private final int value;

    public ProfileStatResponseDto(String type, int value) {
        this.type = type;
        this.value = value;
    }
}
