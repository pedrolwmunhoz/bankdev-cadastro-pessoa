package com.bankdev.cadastro_pessoa.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
public class SaldoDTO {
    @DecimalMin(value = "0.0", inclusive = true, message = "Saldo atual não pode ser negativo")
    private BigDecimal saldoAtual;

    @DecimalMin(value = "0.0", inclusive = true, message = "Limite de crédito deve ser não negativo")
    private BigDecimal limiteCredito;

    @NotNull(message = "Data de última atualização não pode ser nula")
    private LocalDate dataUltimaAtualizacao;
}
