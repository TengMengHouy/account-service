package co.istad.chhaya.account.rest.controller;

import co.istad.chhaya.account.application.ports.input.service.AccountService;
import co.istad.chhaya.account.application.dto.create.CreateAccountRequest;
import co.istad.chhaya.account.application.dto.create.CreateAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public CreateAccountResponse createAccount(
            @RequestBody CreateAccountRequest createAccountRequest
    ) {
        return accountService.createAccount(createAccountRequest);
    }

}
