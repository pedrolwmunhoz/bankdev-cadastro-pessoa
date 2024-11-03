package com.bankdev.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name = "historico_login")
@Data
public class HistoricoLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorico;

    @ManyToOne
    @JoinColumn(name = "id_login", nullable = false)
    private Login login;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;

    private String descricao;
}