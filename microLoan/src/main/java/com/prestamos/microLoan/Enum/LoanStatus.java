package com.prestamos.microLoan.Enum;

public enum LoanStatus {
    PENDING,    // Préstamo pendiente de aprobación
    APPROVED,   // Préstamo aprobado pero aún no activo
    REJECTED,   // Préstamo rechazado
    ACTIVE,     // Préstamo activo y en proceso de pago
    PAID,       // Préstamo completamente pagado
    DEFAULTED   // Préstamo en mora
}
