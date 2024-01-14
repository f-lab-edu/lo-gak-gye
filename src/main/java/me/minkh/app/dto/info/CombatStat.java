package me.minkh.app.dto.info;

import lombok.Data;

@Data
public class CombatStat {

    private final String type;

    private final int value;

    public CombatStat(String type, int value) {
        this.type = type;
        this.value = value;
    }
}
