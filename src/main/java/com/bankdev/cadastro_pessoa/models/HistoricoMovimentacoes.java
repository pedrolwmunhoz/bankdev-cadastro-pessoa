package com.bankdev.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_movimentacoes")
@Data
public class HistoricoMovimentacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimentacao;

    @ManyToOne
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