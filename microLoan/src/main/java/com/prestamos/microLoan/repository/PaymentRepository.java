package com.prestamos.microLoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prestamos.microLoan.models.LoansEntity;
import com.prestamos.microLoan.models.PaymentEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findByDueDateLessThanEqualAndPaidFalse(LocalDate date);
    long countByLoanAndPaidFalse(LoansEntity loan);
}
