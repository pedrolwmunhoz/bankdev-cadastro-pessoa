package com.bankdev.cadastro_pessoa.controller;

import com.bankdev.cadastro_pessoa.models.HistoricoMovimentacoes;
import com.bankdev.cadastro_pessoa.models.Saldo;
import com.bankdev.cadastro_pessoa.repositories.SaldoRepository;
import com.bankdev.cadastro_pessoa.services.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    @Autowired
    private ExtratoService extratoService;

    @Autowired
    private SaldoRepository saldoRepository;

    @GetMapping
    public ResponseEntity<List<HistoricoMovimentacoes>> obterExtratoUltimos30Dias(@RequestParam Integer idSaldo) {
        Saldo saldo = saldoRepository.findById(idSaldo)
                .orElseThrow(() -> new RuntimeException("Saldo não encontrado"));
        
        List<HistoricoMovimentacoes> extrato = extratoService.obterExtratoUltimos30Dias(saldo);
        return ResponseEntity.ok(extrato);
    } 
}
