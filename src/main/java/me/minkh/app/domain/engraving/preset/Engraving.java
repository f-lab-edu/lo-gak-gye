package me.minkh.app.domain.engraving.preset;

import jakarta.persistence.Entity;
import lombok.Getter;
import me.minkh.app.domain.model.BaseEntity;

@Getter
@Entity
public class Engraving extends BaseEntity {

    private String name;

    private int level;
}
