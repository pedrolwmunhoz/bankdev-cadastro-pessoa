package com.devbank.cadastro_pessoa.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbank.cadastro_pessoa.dto.PessoaCadastroDTO;
import com.devbank.cadastro_pessoa.services.CadastroPessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private CadastroPessoaService cadastroPessoaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPessoa(@RequestBody @Valid PessoaCadastroDTO pssoaCadastroDTO ) {
        try {
            cadastroPessoaService.cadastrarPessoa(pssoaCadastroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
