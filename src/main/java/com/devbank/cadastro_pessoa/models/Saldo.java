package com.devbank.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saldo")
@Data
public class Saldo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaldo;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(nullable = false)
    private BigDecimal saldoAtual;

    @Column(nullable = false)
    private BigDecimal limiteCredito;

    @Column(nullable = false)
    private LocalDate dataUltimaAtualizacao;
}