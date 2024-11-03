package com.devbank.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.Valid;

@Data
public class PessoaLoginReturnDTO {

    @Valid
    private PessoaFisicaDTO pessoaFisicaDTO;
    
    @Valid
    private PessoaJuridicaDTO pessoaJuridicaDTO;
}
