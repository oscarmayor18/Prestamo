package com.prestamos.microLoan.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class PaymentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "loan_id", nullable = false)
	private LoansEntity loan; // Préstamo asociado a este pago

	@Column(nullable = false)
	private LocalDate dueDate; // Fecha de vencimiento del pago

	@Column(nullable = false)
	private BigDecimal amount; // Monto del pago

	@Column(nullable = false)
	private boolean paid; // Indica si el pago ha sido realizado

	@Column
	private LocalDate paymentDate; // Fecha en que se realizó el pago (si se ha pagado)

	// Nuevo campo para el número del pago
	@Column
	private Integer paymentNumber;

	public Integer getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoansEntity getLoan() {
		return loan;
	}

	public void setLoan(LoansEntity loan) {
		this.loan = loan;
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

	public PaymentEntity(Long id, LoansEntity loan, LocalDate dueDate, BigDecimal amount, boolean paid,
			LocalDate paymentDate, Integer paymentNumber) {
		super();
		this.id = id;
		this.loan = loan;
		this.dueDate = dueDate;
		this.amount = amount;
		this.paid = paid;
		this.paymentDate = paymentDate;
		this.paymentNumber = paymentNumber;
	}

	public PaymentEntity() {
	}

	// Getters, setters, constructors

}