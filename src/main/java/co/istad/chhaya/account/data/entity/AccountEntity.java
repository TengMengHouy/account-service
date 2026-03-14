package co.istad.chhaya.account.data.entity;

import co.istad.chhaya.common.domain.valueobject.AccountStatus;
import co.istad.chhaya.common.domain.valueobject.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

    @Id
    private UUID accountId;

    private UUID customerId;
    private UUID branchId;

    private String accountNumber;
    private String accountHolder;

    @Embedded
    private Money balance;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountTypeEntity accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;

}
