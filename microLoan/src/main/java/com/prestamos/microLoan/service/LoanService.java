package com.prestamos.microLoan.service;

import java.util.List;

import com.prestamos.microLoan.dto.LoanDTO;
import com.prestamos.microLoan.dto.PaymentDTO;

public interface LoanService {
	List<LoanDTO> findByClientId(Long clientId);
    List<LoanDTO> getLoansByClientId(Long clientId);
    void deleteLoan(Long id);
    LoanDTO updateLoan(LoanDTO loanDetails);
    LoanDTO getLoanById(Long id);
    List<LoanDTO> getAllLoans();
    LoanDTO createLoan(LoanDTO loan);
    void processPayments();
    void recordPayment(Long loanId, PaymentDTO paymentDTO);

}
