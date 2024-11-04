package com.devbank.cadastro_pessoa.dto;

import lombok.Data;

@Data
public class PessoaLoginResponseDTO {

    private Integer idPessoa;

    private SaldoDTO saldoDTO;

    private PessoaFisicaDTO pessoaFisicaDTO;
    
    private PessoaJuridicaDTO pessoaJuridicaDTO;
}
