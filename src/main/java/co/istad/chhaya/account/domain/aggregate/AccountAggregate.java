package co.istad.chhaya.account.domain.aggregate;

import co.istad.chhaya.account.domain.command.CreateAccountCommand;
import co.istad.chhaya.account.domain.exception.AccountDomainException;
import co.istad.chhaya.account.domain.validate.AccountValidate;
import co.istad.chhaya.common.domain.event.AccountCreatedEvent;
import co.istad.chhaya.common.domain.valueobject.AccountStatus;
import co.istad.chhaya.common.domain.valueobject.AccountTypeCode;
import co.istad.chhaya.common.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Aggregate
@NoArgsConstructor // required
@Getter
@EqualsAndHashCode
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private AccountId accountId;

    private String accountNumber;
    private String accountHolder;

    private CustomerId customerId;
    private BranchId branchId;
    private AccountTypeCode accountTypeCode;
    private Money balance;

    private AccountStatus status;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;

    private void validateAccountType(AccountTypeCode accountTypeCode) {
        if (accountTypeCode != AccountTypeCode.SAVING) {
            throw new AccountDomainException("Account type can be only SAVING.");
        }
    }

    private void validateInitialBalance(Money initialBalance) {
        Money minInitialBalance = new Money(BigDecimal.valueOf(10), Currency.USD);
        if (initialBalance.isLessThan(minInitialBalance)) {
            throw new AccountDomainException("Create new account is required 10$");
        }
    }


    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("Aggregate on create account command: {}", createAccountCommand);

        // validate account number
        AccountValidate.validateAccountNumber(createAccountCommand.accountNumber());

        // validate account type code
        validateAccountType(createAccountCommand.accountTypeCode());

        // validate balance
        validateInitialBalance(createAccountCommand.initialBalance());

        // create event object
        AccountCreatedEvent accountCreatedEvent = AccountCreatedEvent.builder()
                .accountId(createAccountCommand.accountId())
                .accountNumber(createAccountCommand.accountNumber())
                .accountHolder(createAccountCommand.accountHolder())
                .branchId(createAccountCommand.branchId())
                .customerId(createAccountCommand.customerId())
                .accountTypeCode(createAccountCommand.accountTypeCode())
                .initialBalance(createAccountCommand.initialBalance())
                .createdAt(ZonedDateTime.now())
                .createdBy("ADMIN")
                .status(AccountStatus.ACTIVE)
                .build();

        // apply aggregate lifecycle
        AggregateLifecycle.apply(accountCreatedEvent);
    }


    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        this.accountId = accountCreatedEvent.accountId();
        this.accountHolder = accountCreatedEvent.accountHolder();
        this.accountNumber = accountCreatedEvent.accountNumber();
        this.accountTypeCode = accountCreatedEvent.accountTypeCode();
        this.balance = accountCreatedEvent.initialBalance();
        this.customerId = accountCreatedEvent.customerId();
        this.branchId = accountCreatedEvent.branchId();
        this.createdAt = accountCreatedEvent.createdAt();
        this.createdBy = accountCreatedEvent.createdBy();
        this.status = accountCreatedEvent.status();
    }

}
