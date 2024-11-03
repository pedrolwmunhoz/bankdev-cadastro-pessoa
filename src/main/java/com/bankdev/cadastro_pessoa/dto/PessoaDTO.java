package com.bankdev.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PessoaDTO {

    private String tipo;
    
    @NotBlank(message = "Telefone não pode estar em branco")
    @Size(min = 11, message = "Telefone deve ter no mínimo 11 caracteres")
    private String telefone;

    @NotBlank(message = "Email não pode estar em branco")
    @Email(message = "Email deve ser válido")
    private String email;
}
