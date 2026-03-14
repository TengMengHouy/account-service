package co.istad.chhaya.account.rest.exception;

import co.istad.chhaya.account.domain.exception.AccountDomainException;
import co.istad.chhaya.common.application.exception.ErrorResponse;
import co.istad.chhaya.common.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class AccountException {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
