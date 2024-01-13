package me.minkh.app.dto.info;

import lombok.Data;

@Data
public class EngravingResponseDto {

    private final String name;

    private final int level;

    public EngravingResponseDto(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
