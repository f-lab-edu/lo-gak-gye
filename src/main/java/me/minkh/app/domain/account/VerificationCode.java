package me.minkh.app.domain.account;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minkh.app.domain.model.BaseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class VerificationCode extends BaseEntity {

    private String code;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    private LocalDateTime expiredDate;

    public void update(String code, Account account, LocalDateTime expiredDate) {
        this.code = code;
        this.account = account;
        this.expiredDate = expiredDate;
    }

    public boolean isCodeMisMatch(String code) {
        return !this.code.equals(code);
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(this.expiredDate);
    }
}
