package com.bankdev.cadastro_pessoa.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class LoginDTO {
    @NotBlank(message = "Email não pode estar em branco")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha não pode estar em branco")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;
}
