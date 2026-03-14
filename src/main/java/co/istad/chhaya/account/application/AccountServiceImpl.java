package co.istad.chhaya.account.application;

import co.istad.chhaya.account.application.dto.create.CreateAccountRequest;
import co.istad.chhaya.account.application.dto.create.CreateAccountResponse;
import co.istad.chhaya.account.application.mapper.AccountMapper;
import co.istad.chhaya.account.application.ports.input.service.AccountService;
import co.istad.chhaya.account.domain.command.CreateAccountCommand;
import co.istad.chhaya.common.domain.valueobject.AccountId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService {


    private final CommandGateway commandGateway;
    private final AccountMapper accountMapper;


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        log.info("createAccountRequest: {}", createAccountRequest);

        AccountId newAccountId = new AccountId(UUID.randomUUID());
        CreateAccountCommand createAccountCommand = accountMapper
                .createAccountRequestToCreateAccountCommand(newAccountId,
                        createAccountRequest);

        commandGateway.sendAndWait(createAccountCommand);

        return CreateAccountResponse.builder()
                .accountId(newAccountId.toString())
                .accountNumber(createAccountCommand.accountNumber())
                .accountHolder(createAccountRequest.accountHolder())
                .message("Account has been created successfully.")
                .build();
    }

}
