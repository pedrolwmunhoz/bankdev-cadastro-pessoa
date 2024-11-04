package com.devbank.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "historico_movimentacoes")
@Data
public class HistoricoMovimentacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimentacao;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_saldo", nullable = false)
    private Saldo saldo;

    @Column(nullable = false)
    private String tipoMovimentacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    private String descricao;
}