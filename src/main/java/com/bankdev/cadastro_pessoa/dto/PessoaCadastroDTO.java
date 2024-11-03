package com.bankdev.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.Valid;

@Data
public class PessoaCadastroDTO {

    @Valid
    private PessoaDTO pessoa;

    @Valid
    private PessoaFisicaDTO pessoaFisicaDTO;
    
    @Valid
    private PessoaJuridicaDTO pessoaJuridicaDTO;

    @Valid
    private LoginDTO login;

    @Valid
    private EnderecoDTO endereco;

    @Valid
    private SaldoDTO saldo;
}
