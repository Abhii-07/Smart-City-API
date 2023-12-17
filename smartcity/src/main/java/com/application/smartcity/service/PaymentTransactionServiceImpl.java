package com.application.smartcity.service;

import com.application.smartcity.exception.PaymentTransactionException;
import com.application.smartcity.model.PaymentTransaction;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.model.User;
import com.application.smartcity.repository.PaymentTransactionRepository;
import com.application.smartcity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    @Autowired
    PaymentTransactionRepository paymentTransactionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PaymentTransaction performTransaction(Integer userId, Double amount) throws PaymentTransactionException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PaymentTransactionException("User with ID " + userId + " not found!"));

        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setAmount(amount);
        transaction.setUser(user);
        transaction.setTransactionDate(new Date());

        return paymentTransactionRepository.save(transaction);
    }

    @Override
    public PaymentTransaction getTransactionById(Integer transactionId) throws PaymentTransactionException {
        return paymentTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new PaymentTransactionException("Transaction with ID " + transactionId + " not found!"));
    }

    @Override
    public List<PaymentTransaction> getAllTransactions() throws PaymentTransactionException {
        return paymentTransactionRepository.findAll();
    }

    @Override
    public List<PaymentTransaction> getTransactionsByService( ServiceType serviceType) throws PaymentTransactionException {

        return paymentTransactionRepository.findByServiceType(serviceType);
    }

    @Override
    public PaymentTransaction makeServiceTransaction(Integer userId, ServiceType serviceType, Double amount) throws PaymentTransactionException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PaymentTransactionException("User with ID " + userId + " not found!"));

        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setAmount(amount);
        transaction.setUser(user);
        transaction.setServiceType(serviceType);
        transaction.setTransactionDate(new Date());

        return paymentTransactionRepository.save(transaction);
    }

    @Override
    public void refundTransaction(Integer transactionId) throws PaymentTransactionException {
        PaymentTransaction transaction = getTransactionById(transactionId);
        paymentTransactionRepository.delete(transaction);
    }

//    @Override
//    public void cancelServiceTransaction(Integer userId, ServiceType serviceType) throws PaymentTransactionException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new PaymentTransactionException("User with ID " + userId + " not found!"));
//
//        List<PaymentTransaction> userTransactions = paymentTransactionRepository.findByUser(user);
//
//        // Cancel transactions matching the service type
//        for (PaymentTransaction transaction : userTransactions) {
//            if (transaction.getServiceType() == serviceType) {
//                paymentTransactionRepository.delete(transaction);
//            }
//        }
//    }


    @Override
    public Double calculateTotalSpending(Integer userId) throws PaymentTransactionException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PaymentTransactionException("User with ID " + userId + " not found!"));

        List<PaymentTransaction> transactions = paymentTransactionRepository.findByUser(user);
        double totalSpending = transactions.stream().mapToDouble(PaymentTransaction::getAmount).sum();

        return totalSpending;
    }

    @Override
    public List<PaymentTransaction> getTransactionsByDateRange(Date startDate, Date endDate) throws PaymentTransactionException {
        return paymentTransactionRepository.findByTransactionDateBetween(startDate, endDate);
    }
}
