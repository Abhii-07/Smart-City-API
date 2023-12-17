package com.application.smartcity.service;

import com.application.smartcity.exception.PaymentTransactionException;
import com.application.smartcity.model.PaymentTransaction;
import com.application.smartcity.model.ServiceType;

import java.util.Date;
import java.util.List;

public interface PaymentTransactionService {
    PaymentTransaction performTransaction(Integer userId, Double amount) throws PaymentTransactionException;
    PaymentTransaction getTransactionById(Integer transactionId) throws PaymentTransactionException;
    List<PaymentTransaction> getAllTransactions() throws PaymentTransactionException;

    List<PaymentTransaction> getTransactionsByService(ServiceType serviceType) throws PaymentTransactionException;

    PaymentTransaction makeServiceTransaction(Integer userId, ServiceType serviceType, Double amount) throws PaymentTransactionException;

    void refundTransaction(Integer transactionId) throws PaymentTransactionException;
//    void cancelServiceTransaction(Integer userId, ServiceType serviceType) throws PaymentTransactionException;
    Double calculateTotalSpending(Integer userId) throws PaymentTransactionException;
    List<PaymentTransaction> getTransactionsByDateRange(Date startDate, Date endDate) throws PaymentTransactionException;
}
