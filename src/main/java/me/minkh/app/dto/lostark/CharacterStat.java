package me.minkh.app.dto.lostark;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterStat {

    private int critical;

    private int swiftness;

    public CharacterStat(int critical, int swiftness) {
        this.critical = critical;
        this.swiftness = swiftness;
    }
}
