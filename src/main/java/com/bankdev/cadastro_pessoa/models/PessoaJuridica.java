package com.bankdev.cadastro_pessoa.models;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_juridica")
@Data
public class PessoaJuridica extends Pessoa{

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(unique = true, nullable = false)
    private String cnpj;
}