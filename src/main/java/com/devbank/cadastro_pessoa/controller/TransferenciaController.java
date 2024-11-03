package com.devbank.cadastro_pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devbank.cadastro_pessoa.services.TransferenciaService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<String> realizarTransferencia(@RequestParam Integer idOrigem,
                                                        @RequestParam Integer idDestino,
                                                        @RequestParam BigDecimal valor) {
        try {
            String resultado = transferenciaService.realizarTransferencia(idOrigem, idDestino, valor);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
