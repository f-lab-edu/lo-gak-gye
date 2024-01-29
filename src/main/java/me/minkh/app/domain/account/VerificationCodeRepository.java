package me.minkh.app.domain.account;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface VerificationCodeRepository extends Repository<VerificationCode, Long> {

    VerificationCode save(VerificationCode verificationCode);

    Optional<VerificationCode> findByAccount(Account account);

    void deleteAll();
}
