package com.prestamos.microLoan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.prestamos.microLoan.Enum.LoanStatus;
import com.prestamos.microLoan.Enum.PaymentFrequency;

public class LoanDTO {
    private Long id;
    private Long clientId;
    private BigDecimal principal;
    private BigDecimal interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LoanStatus status;
    private PaymentFrequency paymentFrequency;
    private List<PaymentDTO> payments;
    // getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LoanStatus getStatus() {
		return status;
	}
	public void setStatus(LoanStatus status) {
		this.status = status;
	}
	public PaymentFrequency getPaymentFrequency() {
		return paymentFrequency;
	}
	public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}
	public List<PaymentDTO> getPayments() {
		return payments;
	}
	public void setPayments(List<PaymentDTO> payments) {
		this.payments = payments;
	}
    
}