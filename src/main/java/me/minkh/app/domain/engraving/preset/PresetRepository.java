package me.minkh.app.domain.engraving.preset;

import me.minkh.app.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PresetRepository extends JpaRepository<Preset, Long> {

    List<Preset> findByAccount(Account account);

    Optional<Preset> findByIdAndAccount(Long presetId, Account account);
}
