package me.minkh.app.dto.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EquipmentResponseDto {

    private String artifact;

    public EquipmentResponseDto(String artifact) {
        this.artifact = artifact;
    }
}
