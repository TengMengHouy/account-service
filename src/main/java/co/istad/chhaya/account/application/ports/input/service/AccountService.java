package co.istad.chhaya.account.application.ports.input.service;

import co.istad.chhaya.account.application.dto.create.CreateAccountRequest;
import co.istad.chhaya.account.application.dto.create.CreateAccountResponse;

public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

}
