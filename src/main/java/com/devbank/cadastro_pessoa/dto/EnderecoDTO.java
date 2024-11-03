package com.devbank.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class EnderecoDTO {
    @NotBlank(message = "Logradouro não pode estar em branco")
    private String logradouro;

    @NotBlank(message = "Número não pode estar em branco")
    private String numero;

    private String complemento;

    @NotBlank(message = "Bairro não pode estar em branco")
    private String bairro;

    @NotBlank(message = "Cidade não pode estar em branco")
    private String cidade;

    @NotBlank(message = "Estado não pode estar em branco")
    private String estado;

    @NotBlank(message = "CEP não pode estar em branco")
    @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos")
    private String cep;
}
