package co.istad.chhaya.account.application.dto.create;

import lombok.Builder;

@Builder
public record CreateAccountResponse(
        String accountId,
        String accountNumber,
        String accountHolder,
        String message
) {
}
