package com.devbank.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PessoaFisicaDTO extends PessoaDTO {
    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;

    @NotNull(message = "Idade não pode ser nula")
    @Min(value = 18, message = "Idade mínima é 18 anos")
    private Integer idade;

    @NotBlank(message = "CPF não pode estar em branco")
    @NotNull(message = "CPF não pode estar em branco")
    @Size(min = 11, message = "CPF deve ter no mínimo 11 caracteres")
    private String cpf;

}
