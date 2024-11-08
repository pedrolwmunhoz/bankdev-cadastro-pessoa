package com.devbank.cadastro_pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devbank.cadastro_pessoa.dto.PessoaLoginResponseDTO;
import com.devbank.cadastro_pessoa.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/validar")
    public ResponseEntity<Object> validarLogin(@RequestParam String email, @RequestParam String senha) {

        PessoaLoginResponseDTO pessoaLoginResponseDTO = loginService.validarLogin(email, senha);

        if (pessoaLoginResponseDTO.getPessoaFisicaDTO() != null || pessoaLoginResponseDTO.getPessoaJuridicaDTO() != null) {
            return ResponseEntity.ok(pessoaLoginResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
