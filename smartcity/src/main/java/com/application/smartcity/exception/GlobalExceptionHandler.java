package com.application.smartcity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> handleUserException(UserException ue, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), ue.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<MyErrorDetails> handleWalletException(WalletException we, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), we.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentTransactionException.class)
    public ResponseEntity<MyErrorDetails> handlePaymentTransactionException(PaymentTransactionException pte, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(), pte.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

