package me.minkh.app.dto.lostark;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CharacterEquipment {

    private String artifact;

    public CharacterEquipment(String artifact) {
        this.artifact = artifact;
    }
}
