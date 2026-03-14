package co.istad.chhaya.account.data.entity;

import co.istad.chhaya.common.domain.valueobject.AccountTypeCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account_types")
@Getter
@Setter
@NoArgsConstructor
public class AccountTypeEntity {

    @Id
    private UUID accountTypeId;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode code;

    @OneToMany(mappedBy = "accountType")
    private List<AccountEntity> accounts;

}
