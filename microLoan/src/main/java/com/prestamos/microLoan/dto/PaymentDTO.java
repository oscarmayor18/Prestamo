package com.prestamos.microLoan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDTO {
    private Long id;
    private LocalDate dueDate;
    private BigDecimal amount;
    private boolean paid;
    private LocalDate paymentDate;
    private Integer paymentNumber; // Nuevo campo

    
    public Integer getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
    
}
