package com.devbank.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @Column(nullable = false)
    private String tipo;

    @Column(unique = true, nullable = false)
    private String numeroConta;

    @Column(unique = true, nullable = false)
    private String telefone;

    @Column(unique = true, nullable = false)
    private String email;
}
