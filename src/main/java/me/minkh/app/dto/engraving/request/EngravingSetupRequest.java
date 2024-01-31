package me.minkh.app.dto.engraving.request;

import lombok.Data;

import java.util.List;

@Data
public class EngravingSetupRequest {

    private String artifact;

    private Elixir elixir;

    private List<CombatStat> combatStats;

    private List<Engraving> engravings;

    private Etc etc;
}

