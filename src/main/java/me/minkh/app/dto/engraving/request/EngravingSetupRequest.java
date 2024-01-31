package me.minkh.app.dto.engraving.request;

import lombok.Data;

import java.util.List;

@Data
public class EngravingSetupRequest {

    private String artifact;

    private ElixirDto elixir;

    private List<CombatStatDto> combatStats;

    private List<EngravingDto> engravings;

    private EtcDto etc;
}

