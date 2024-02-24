package com.zote.common.utls;

import com.zote.common.utls.exceptions.AgentNotFoundException;
import com.zote.common.utls.exceptions.ErrorMessage;
import com.zote.common.utls.exceptions.FunctionalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = FunctionalError.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage wrongCredentialException(final FunctionalError e) {
        return new ErrorMessage(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = AgentNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage wrongCredentialException(final AgentNotFoundException e) {
        return new ErrorMessage(BAD_REQUEST.value(), e.getMessage());
    }
}
