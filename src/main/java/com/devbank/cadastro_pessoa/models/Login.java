package com.devbank.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "login")
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogin;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(unique = true, name = "id_pessoa", nullable = false)
    private Pessoa pessoa;
}
