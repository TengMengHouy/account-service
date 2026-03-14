package co.istad.chhaya.account.domain.command;

import co.istad.chhaya.common.domain.valueobject.AccountTypeCode;
import co.istad.chhaya.common.domain.valueobject.AccountId;
import co.istad.chhaya.common.domain.valueobject.BranchId;
import co.istad.chhaya.common.domain.valueobject.CustomerId;
import co.istad.chhaya.common.domain.valueobject.Money;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,

        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance
) {
}
