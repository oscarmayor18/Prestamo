package com.prestamos.microLoan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prestamos.microLoan.dto.LoanDTO;
import com.prestamos.microLoan.dto.PaymentDTO;
import com.prestamos.microLoan.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
    	LoanDTO loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/client/{clientId}")
    public List<LoanDTO> getLoansByClientId(@PathVariable Long clientId) {
        return loanService.getLoansByClientId(clientId);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loan) {
    	LoanDTO createdLoan = loanService.createLoan(loan);
        return ResponseEntity.ok(createdLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDetails) {
        loanDetails.setId(id);
        LoanDTO updatedLoan = loanService.updateLoan(loanDetails);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/process-payments")
    public ResponseEntity<?> processPayments() {
        loanService.processPayments();
        return ResponseEntity.ok().build();
    }
    

    @PostMapping("/{id}/payments")
    public ResponseEntity<?> recordPayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        loanService.recordPayment(id, paymentDTO);
        return ResponseEntity.ok().build();
    }
}