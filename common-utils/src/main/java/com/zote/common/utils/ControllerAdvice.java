package com.zote.common.utils;

import com.zote.common.utils.exceptions.AgentNotFoundException;
import com.zote.common.utils.exceptions.ErrorMessage;
import com.zote.common.utils.exceptions.FunctionalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
