package me.minkh.app.domain.account;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String key;

    public Account() {
    }

    @Builder
    public Account(Long id, String name, String key) {
        this.id = id;
        this.name = name;
        this.key = key;
    }
}
