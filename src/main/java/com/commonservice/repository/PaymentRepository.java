package com.commonservice.repository;

import com.commonservice.entity.Payment;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Payment findPaymentByTransactionId(String transactionId);
}
