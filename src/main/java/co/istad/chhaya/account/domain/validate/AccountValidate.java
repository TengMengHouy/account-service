package co.istad.chhaya.account.domain.validate;

import co.istad.chhaya.account.domain.exception.AccountDomainException;

public class AccountValidate {

    public static void validateAccountNumber(String accountNumber) {
        if (accountNumber == null) {
            throw new AccountDomainException("Account number cannot be null");
        }

        if (!accountNumber.matches("^\\d{9}$")) {
            throw new AccountDomainException("Account number is invalid");
        }
    }

}
