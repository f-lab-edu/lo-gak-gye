package me.minkh.app.dto.info;

import lombok.Data;

import java.util.List;

@Data
public class InfoResponse {

      private final String artifact;

      private final List<CombatStat> combatStats;

      private final List<Engraving> engravings;

      public InfoResponse(String artifact, List<CombatStat> combatStats, List<Engraving> engravings) {
            this.artifact = artifact;
            this.combatStats = combatStats;
            this.engravings = engravings;
      }
}

