package com.application.smartcity.repository;

import com.application.smartcity.model.PaymentTransaction;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Integer> {
    List<PaymentTransaction> findByServiceType(ServiceType serviceType);


    List<PaymentTransaction> findByTransactionDateBetween(Date startDate, Date endDate);
    List<PaymentTransaction> findByUserAssociatedServices(ServiceType serviceType);

    List<PaymentTransaction> findByUser(User user);
}
