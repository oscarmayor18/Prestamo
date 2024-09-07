package com.prestamos.microLoan.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.prestamos.microLoan.Enum.LoanStatus;
import com.prestamos.microLoan.Enum.PaymentFrequency;

@Entity
@Table(name = "loans")
public class LoansEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long clientId;  // ID del cliente asociado al préstamo

    @NotNull
    @Column(nullable = false)
    private BigDecimal principal;  // Monto principal del préstamo

    @NotNull
    @Column(nullable = false)
    private BigDecimal interestRate;  // Tasa de interés anual

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;  // Fecha de inicio del préstamo

    @NotNull
    @Column(nullable = false)
    private LocalDate endDate;  // Fecha de finalización del préstamo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;  // Estado actual del préstamo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentFrequency paymentFrequency;  // Frecuencia de pago (mensual o quincenal)

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentEntity> payments = new ArrayList<>();  // Lista de pagos asociados al préstamo

    @Column(nullable = false)
    private BigDecimal totalAmount;  // Monto total a pagar (principal + intereses)

    @Column(nullable = false)
    private int numberOfPayments;  // Número total de pagos

    @Column(nullable = false)
    private BigDecimal paymentAmount;  // Monto de cada pago

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

	public List<PaymentEntity> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentEntity> payments) {
		this.payments = payments;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public LoansEntity(Long id, @NotNull Long clientId, @NotNull BigDecimal principal, @NotNull BigDecimal interestRate,
			@NotNull LocalDate startDate, @NotNull LocalDate endDate, LoanStatus status,
			PaymentFrequency paymentFrequency, List<PaymentEntity> payments, BigDecimal totalAmount,
			int numberOfPayments, BigDecimal paymentAmount) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.principal = principal;
		this.interestRate = interestRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.paymentFrequency = paymentFrequency;
		this.payments = payments;
		this.totalAmount = totalAmount;
		this.numberOfPayments = numberOfPayments;
		this.paymentAmount = paymentAmount;
	}

	public LoansEntity() {
	}

    // Getters, setters, constructors
    	
}



