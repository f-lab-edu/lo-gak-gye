package me.minkh.app.dto.info;

import lombok.Getter;
import me.minkh.app.dto.lostark.CharacterEngraving;
import me.minkh.app.dto.lostark.CharacterEquipment;
import me.minkh.app.dto.lostark.CharacterStat;

@Getter
public class InfoResponseDto {

      private final String artifact;

      private final int critical;

      private final int swiftness;

      private final int cursedDoll;

      private final int adrenaline;

      public InfoResponseDto(CharacterEquipment equipment, CharacterStat stat, CharacterEngraving engraving) {
            this.artifact = equipment.getArtifact();
            this.critical = stat.getCritical();
            this.swiftness = stat.getSwiftness();
            this.cursedDoll = engraving.getCursedDoll();
            this.adrenaline = engraving.getAdrenaline();
      }
}

