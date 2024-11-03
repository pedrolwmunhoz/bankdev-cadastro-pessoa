package com.bankdev.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_fisica")
@Data
public class PessoaFisica extends Pessoa{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(unique = true, nullable = false)
    private String cpf;

}
