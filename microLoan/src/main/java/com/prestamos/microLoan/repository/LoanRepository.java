package com.prestamos.microLoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prestamos.microLoan.models.LoansEntity;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoansEntity, Long> {
    List<LoansEntity> findByClientId(Long clientId);
}
