package me.minkh.app.dto.lostark;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterEngraving {

    private int cursedDoll;

    private int adrenaline;

    public CharacterEngraving(int cursedDoll, int adrenaline) {
        this.cursedDoll = cursedDoll;
        this.adrenaline = adrenaline;
    }
}
