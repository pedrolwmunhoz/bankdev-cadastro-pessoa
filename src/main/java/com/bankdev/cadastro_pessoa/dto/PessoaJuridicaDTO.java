package com.bankdev.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PessoaJuridicaDTO extends PessoaDTO {
    @NotBlank(message = "Razão social não pode estar em branco")
    private String razaoSocial;

    @NotBlank(message = "Nome fantasia não pode estar em branco")
    private String nomeFantasia;

    @NotBlank(message = "CNPJ não pode estar em branco")
    @NotNull(message = "CNPJ não pode estar em branco")
    @Size(min = 14, message = "CNPJ deve ter no mínimo 14 caracteres")
    private String cnpj;

}