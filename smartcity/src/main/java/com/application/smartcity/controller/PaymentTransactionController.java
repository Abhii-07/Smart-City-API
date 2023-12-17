package com.application.smartcity.controller;

import com.application.smartcity.exception.PaymentTransactionException;
import com.application.smartcity.model.PaymentTransaction;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.service.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @PostMapping("/performTransaction")
    public ResponseEntity<PaymentTransaction> performTransaction(@RequestParam Integer userId, @RequestParam Double amount) {
        try {
            PaymentTransaction transaction = paymentTransactionService.performTransaction(userId, amount);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<PaymentTransaction> getTransactionById(@PathVariable Integer transactionId) {
        try {
            PaymentTransaction transaction = paymentTransactionService.getTransactionById(transactionId);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentTransaction>> getAllTransactions() {
        try {
            List<PaymentTransaction> transactions = paymentTransactionService.getAllTransactions();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/service/{serviceType}")
    public ResponseEntity<List<PaymentTransaction>> getTransactionsByService(@PathVariable ServiceType serviceType) {
        try {
            List<PaymentTransaction> transactions = paymentTransactionService.getTransactionsByService(serviceType);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/makeServiceTransaction")
    public ResponseEntity<PaymentTransaction> makeServiceTransaction(@RequestParam Integer userId,
                                                                     @RequestParam ServiceType serviceType,
                                                                     @RequestParam Double amount) {
        try {
            PaymentTransaction transaction = paymentTransactionService.makeServiceTransaction(userId, serviceType, amount);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/refund/{transactionId}")
    public ResponseEntity<Void> refundTransaction(@PathVariable Integer transactionId) {
        try {
            paymentTransactionService.refundTransaction(transactionId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/totalSpending/{userId}")
    public ResponseEntity<Double> calculateTotalSpending(@PathVariable Integer userId) {
        try {
            Double totalSpending = paymentTransactionService.calculateTotalSpending(userId);
            return new ResponseEntity<>(totalSpending, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dateRange")
    public ResponseEntity<List<PaymentTransaction>> getTransactionsByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        try {
            List<PaymentTransaction> transactions = paymentTransactionService.getTransactionsByDateRange(startDate, endDate);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (PaymentTransactionException ex) {
            // Handle exception, return appropriate ResponseEntity with error details
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
