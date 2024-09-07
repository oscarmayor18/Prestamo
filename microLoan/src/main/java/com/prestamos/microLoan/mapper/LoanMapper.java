package com.prestamos.microLoan.mapper;

import com.prestamos.microLoan.dto.LoanDTO;
import com.prestamos.microLoan.dto.PaymentDTO;
import com.prestamos.microLoan.models.LoansEntity;
import com.prestamos.microLoan.models.PaymentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    // Convierte una entidad de préstamo a DTO
    public LoanDTO toDTO(LoansEntity entity) {
        LoanDTO dto = new LoanDTO();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClientId());
        dto.setPrincipal(entity.getPrincipal());
        dto.setInterestRate(entity.getInterestRate());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());
        dto.setPaymentFrequency(entity.getPaymentFrequency());
        // Convierte y establece los pagos
        dto.setPayments(entity.getPayments().stream().map(this::toPaymentDTO).collect(Collectors.toList()));
        return dto;
    }

    // Convierte un DTO de préstamo a entidad
    public LoansEntity toEntity(LoanDTO dto) {
        LoansEntity entity = new LoansEntity();
        entity.setId(dto.getId());
        entity.setClientId(dto.getClientId());
        entity.setPrincipal(dto.getPrincipal());
        entity.setInterestRate(dto.getInterestRate());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        entity.setPaymentFrequency(dto.getPaymentFrequency());
        // No establecemos los pagos aquí, ya que se generan en el servicio
        return entity;
    }

    // Convierte una entidad de pago a DTO
    public PaymentDTO toPaymentDTO(PaymentEntity entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setDueDate(entity.getDueDate());
        dto.setAmount(entity.getAmount());
        dto.setPaid(entity.isPaid());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentNumber(entity.getPaymentNumber());
        return dto;
    }

    // Convierte una lista de entidades de préstamo a lista de DTOs
    public List<LoanDTO> toDTOList(List<LoansEntity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}