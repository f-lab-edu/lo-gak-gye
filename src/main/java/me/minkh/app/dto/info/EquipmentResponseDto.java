package me.minkh.app.dto.info;

import lombok.Data;

@Data
public class EquipmentResponseDto {

    private final String artifact;

    public EquipmentResponseDto(String artifact) {
        this.artifact = artifact;
    }
}
