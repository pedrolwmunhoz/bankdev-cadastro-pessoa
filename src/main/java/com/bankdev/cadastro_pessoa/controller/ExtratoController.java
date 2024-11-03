package com.bankdev.cadastro_pessoa.controller;

import com.bankdev.cadastro_pessoa.models.HistoricoMovimentacoes;
import com.bankdev.cadastro_pessoa.models.Pessoa;
import com.bankdev.cadastro_pessoa.models.Saldo;
import com.bankdev.cadastro_pessoa.repositories.PessoaRepository;
import com.bankdev.cadastro_pessoa.repositories.SaldoRepository;
import com.bankdev.cadastro_pessoa.services.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    @Autowired
    private ExtratoService extratoService;

    @Autowired
    private SaldoRepository saldoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<HistoricoMovimentacoes>> obterExtratoUltimos30Dias(@RequestParam Integer idPessoa) {
        // Verifica se a pessoa existe no banco
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(idPessoa);
        
        if (optionalPessoa.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        Pessoa pessoa = optionalPessoa.get();

        // Verifica se o saldo associado à pessoa existe
        Optional<Saldo> optionalSaldo = saldoRepository.findByPessoa(pessoa);
        if (optionalSaldo.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        Saldo saldo = optionalSaldo.get();
        List<HistoricoMovimentacoes> extrato = extratoService.obterExtratoUltimos30Dias(saldo);
        return ResponseEntity.ok(extrato);
    }
}
