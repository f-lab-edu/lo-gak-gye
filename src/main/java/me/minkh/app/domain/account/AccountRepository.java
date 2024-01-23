package me.minkh.app.domain.account;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.function.Supplier;

public interface AccountRepository extends Repository<Account, Long> {

    Account save(Account account);

    Optional<Account> findById(Long id);

    Optional<Account> findByEmail(String email);
}
