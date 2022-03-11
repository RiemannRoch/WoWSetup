package com.riemannroch.wowsetup.config;

import lombok.Builder;
import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    private ResponseEntity<ResponseError> buildResponse(String message, HttpStatus status){
        return new ResponseEntity<>(ResponseError
                .builder()
                .status(status.value())
                .message(message).build(), status);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> handleNotFound(NotFoundException ex){
        return buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @Data
    @Builder
    private static final class ResponseError {
        private int status;
        private String message;
    }
}
