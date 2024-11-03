package com.bankdev.cadastro_pessoa.controller;

import com.bankdev.cadastro_pessoa.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/validar")
    public ResponseEntity<String> validarLogin(@RequestParam String email, @RequestParam String senha) {

        boolean isValid = loginService.validarLogin(email, senha);

        if (isValid) {
            return ResponseEntity.ok("Login válido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}