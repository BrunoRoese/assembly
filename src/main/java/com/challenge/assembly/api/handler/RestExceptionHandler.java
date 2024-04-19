package com.challenge.assembly.api.handler;

import com.challenge.assembly.api.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
