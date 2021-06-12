package com.getir.rig.exception;

import com.getir.rig.domain.model.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(value = {ErrorException.class})
    public ResponseEntity<Error> handlerErrorException(ErrorException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Error error = new Error(e.getMessage(), badRequest);

        return new ResponseEntity<>(error, badRequest);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Error> handlerErrorException(RuntimeException e){
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        Error error = new Error(e.getMessage(), internalServerError);

        return new ResponseEntity<>(error, internalServerError);
    }
}
