package co.istad.chhaya.account.application.dto.create;

import co.istad.chhaya.common.domain.valueobject.AccountTypeCode;
import co.istad.chhaya.common.domain.valueobject.BranchId;
import co.istad.chhaya.common.domain.valueobject.CustomerId;
import co.istad.chhaya.common.domain.valueobject.Money;

public record CreateAccountRequest(
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance
) {
}
