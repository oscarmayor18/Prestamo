package com.prestamos.microLoan.service.Imple;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.prestamos.microLoan.Enum.LoanStatus;
import com.prestamos.microLoan.Enum.PaymentFrequency;
import com.prestamos.microLoan.dto.LoanDTO;
import com.prestamos.microLoan.dto.PaymentDTO;
import com.prestamos.microLoan.mapper.LoanMapper;
import com.prestamos.microLoan.models.LoansEntity;
import com.prestamos.microLoan.models.PaymentEntity;
import com.prestamos.microLoan.repository.LoanRepository;
import com.prestamos.microLoan.repository.PaymentRepository;
import com.prestamos.microLoan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private LoanMapper loanMapper;

	// Busca préstamos por ID de cliente
	@Override
	public List<LoanDTO> findByClientId(Long clientId) {
		return loanMapper.toDTOList(loanRepository.findByClientId(clientId));
	}

	// Obtiene préstamos por ID de cliente (método duplicado, se podría eliminar)
	@Override
	public List<LoanDTO> getLoansByClientId(Long clientId) {
		return loanMapper.toDTOList(loanRepository.findByClientId(clientId));
	}

	// Elimina un préstamo por su ID
	@Override
	public void deleteLoan(Long id) {
		loanRepository.deleteById(id);
	}

	// Actualiza los detalles de un préstamo existente
	@Override
	public LoanDTO updateLoan(LoanDTO loanDetails) {
		// Busca el préstamo existente
		LoansEntity existingLoan = loanRepository.findById(loanDetails.getId()).orElseThrow(
				() -> new ResourceAccessException("No se encontró el préstamo con el id = " + loanDetails.getId()));

		// Actualiza los campos del préstamo
		existingLoan.setPrincipal(loanDetails.getPrincipal());
		existingLoan.setInterestRate(loanDetails.getInterestRate());
		existingLoan.setStartDate(loanDetails.getStartDate());
		existingLoan.setEndDate(loanDetails.getEndDate());
		existingLoan.setStatus(loanDetails.getStatus());
		existingLoan.setPaymentFrequency(loanDetails.getPaymentFrequency());

		// Recalcula los campos derivados
		BigDecimal totalAmount = calculateTotalAmount(existingLoan.getPrincipal(), existingLoan.getInterestRate(),
				existingLoan.getStartDate(), existingLoan.getEndDate());
		existingLoan.setTotalAmount(totalAmount);

		int numberOfPayments = calculateNumberOfPayments(existingLoan.getStartDate(), existingLoan.getEndDate(),
				existingLoan.getPaymentFrequency());
		existingLoan.setNumberOfPayments(numberOfPayments);

		BigDecimal paymentAmount = totalAmount.divide(BigDecimal.valueOf(numberOfPayments), 2, RoundingMode.HALF_UP);
		existingLoan.setPaymentAmount(paymentAmount);

		// Guarda y convierte el préstamo actualizado a DTO
		LoansEntity updatedLoan = loanRepository.save(existingLoan);
		return loanMapper.toDTO(updatedLoan);
	}

	// Obtiene un préstamo por su ID y lo convierte a DTO
	@Override
	public LoanDTO getLoanById(Long id) {
		LoansEntity loan = loanRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));
		return loanMapper.toDTO(loan);
	}

	// Obtiene todos los préstamos y los convierte a DTOs
	@Override
	public List<LoanDTO> getAllLoans() {
		return loanMapper.toDTOList(loanRepository.findAll());
	}

	// Crea un nuevo préstamo
	@Override
	@Transactional
	public LoanDTO createLoan(LoanDTO loanDTO) {
		// Convierte el DTO a entidad
		LoansEntity loan = loanMapper.toEntity(loanDTO);

		// Calcula el monto total del préstamo
		BigDecimal totalAmount = calculateTotalAmount(loan.getPrincipal(), loan.getInterestRate(), loan.getStartDate(),
				loan.getEndDate());
		loan.setTotalAmount(totalAmount);

		// Calcula el número de pagos
		int numberOfPayments = calculateNumberOfPayments(loan.getStartDate(), loan.getEndDate(),
				loan.getPaymentFrequency());
		loan.setNumberOfPayments(numberOfPayments);

		// Calcula el monto de cada pago
		BigDecimal paymentAmount = totalAmount.divide(BigDecimal.valueOf(numberOfPayments), 2, RoundingMode.HALF_UP);
		loan.setPaymentAmount(paymentAmount);

		// Guarda el préstamo
		loan = loanRepository.save(loan);

		// Genera los pagos programados
		generatePayments(loan);

		// Convierte y retorna el préstamo creado como DTO
		return loanMapper.toDTO(loan);
	}

	// Procesa los pagos vencidos
	@Override
	@Transactional
	public void processPayments() {
		LocalDate today = LocalDate.now();
		List<PaymentEntity> duePayments = paymentRepository.findByDueDateLessThanEqualAndPaidFalse(today);

		for (PaymentEntity payment : duePayments) {
			LoansEntity loan = payment.getLoan();
			if (loan.getStatus() == LoanStatus.ACTIVE) {
				payment.setPaid(true);
				payment.setPaymentDate(today);
				paymentRepository.save(payment);

				// Verifica si es el último pago
				if (isLastPayment(loan, payment)) {
					loan.setStatus(LoanStatus.PAID);
					loanRepository.save(loan);
				}
			}
		}
	}

	// Calcula el monto total del préstamo incluyendo intereses
	private BigDecimal calculateTotalAmount(BigDecimal principal, BigDecimal interestRate, LocalDate startDate,
			LocalDate endDate) {
		long days = ChronoUnit.DAYS.between(startDate, endDate);
		BigDecimal interest = principal.multiply(interestRate).multiply(BigDecimal.valueOf(days))
				.divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);
		return principal.add(interest);
	}

	// Calcula el número de pagos basado en la frecuencia y duración del préstamo
	private int calculateNumberOfPayments(LocalDate startDate, LocalDate endDate, PaymentFrequency frequency) {
		long days = ChronoUnit.DAYS.between(startDate, endDate);
		return frequency == PaymentFrequency.MONTHLY ? (int) (days / 30) : (int) (days / 15);
	}

	// Genera los pagos programados para el préstamo
	private void generatePayments(LoansEntity loan) {
	    LocalDate paymentDate = loan.getStartDate();
	    int paymentNumber = 1; // Reinicia la numeración para cada préstamo

	    for (int i = 0; i < loan.getNumberOfPayments(); i++) {
	        PaymentEntity payment = new PaymentEntity();
	        payment.setLoan(loan);
	        payment.setDueDate(paymentDate);
	        payment.setAmount(loan.getPaymentAmount());
	        payment.setPaid(false);
	        payment.setPaymentNumber(paymentNumber); // Asigna el número del pago
	        paymentRepository.save(payment);
	        

	        paymentDate = loan.getPaymentFrequency() == PaymentFrequency.MONTHLY ? paymentDate.plusMonths(1)
	            : paymentDate.plusWeeks(2);

	        paymentNumber++; // Incrementa el número del pago
	        System.out.println(payment.getPaymentNumber());
	}
	}

	// Verifica si este es el último pago del préstamo
	private boolean isLastPayment(LoansEntity loan, PaymentEntity payment) {
		return payment.getDueDate().equals(loan.getEndDate()) || paymentRepository.countByLoanAndPaidFalse(loan) == 1;
	}
	
	@Override
	@Transactional
	public void recordPayment(Long loanId, PaymentDTO paymentDTO) {
	    // Buscar el préstamo por ID
	    LoansEntity loan = loanRepository.findById(loanId)
	        .orElseThrow(() -> new ResourceAccessException("No se encontró el préstamo con el id = " + loanId));

	    // Buscar el pago por ID
	    PaymentEntity payment = paymentRepository.findById(paymentDTO.getId())
	        .orElseThrow(() -> new ResourceAccessException("No se encontró el pago con el id = " + paymentDTO.getId()));

	    // Verificar que el pago pertenece al préstamo correcto
	    if (!payment.getLoan().getId().equals(loanId)) {
	        throw new ResourceAccessException("El pago no pertenece al préstamo especificado.");
	    }

	    // Actualizar el pago
	    payment.setPaid(true);
	    payment.setPaymentDate(paymentDTO.getPaymentDate() != null ? paymentDTO.getPaymentDate() : LocalDate.now());
	    paymentRepository.save(payment);

	    // Verificar si el préstamo está completamente pagado
	    boolean allPaymentsMade = paymentRepository.countByLoanAndPaidFalse(loan) == 0;
	    if (allPaymentsMade) {
	        loan.setStatus(LoanStatus.PAID);
	        loanRepository.save(loan);
	    }
	}


}