package com.challenge.picpay.exception;

import com.challenge.picpay.enums.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<CustomErrorException> handleUnauthorizedException(UnauthorizedException ex){
        CustomErrorException error = new CustomErrorException();
        error.setMessage(ex.getMessage());
        error.setCode(ResponseStatus.UNAUTHORIZED.getHttpReturnCode());
        error.setReason(ResponseStatus.UNAUTHORIZED.getReason());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomErrorException> handleInsufficientBalanceException(InsufficientBalanceException ex){
        CustomErrorException error = new CustomErrorException();
        error.setMessage(ex.getMessage());
        error.setCode(ResponseStatus.NO_CONTENT.getHttpReturnCode());
        error.setReason(ResponseStatus.NO_CONTENT.getReason());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorException> handleNotFoundException(NotFoundException ex){
        CustomErrorException error = new CustomErrorException();
        error.setMessage(ex.getMessage());
        error.setCode(ResponseStatus.NO_CONTENT.getHttpReturnCode());
        error.setReason(ResponseStatus.NO_CONTENT.getReason());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomErrorException> handleBadRequestException(BadRequestException ex){
        CustomErrorException error = new CustomErrorException();
        error.setMessage(ex.getMessage());
        error.setCode(ResponseStatus.BAD_REQUEST.getHttpReturnCode());
        error.setReason(ResponseStatus.BAD_REQUEST.getReason());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
