package co.istad.chhaya.account.application.mapper;

import co.istad.chhaya.account.application.dto.create.CreateAccountRequest;
import co.istad.chhaya.account.domain.command.CreateAccountCommand;
import co.istad.chhaya.common.domain.valueobject.AccountId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateAccountCommand createAccountRequestToCreateAccountCommand(
            AccountId accountId,
            CreateAccountRequest createAccountRequest);

}
