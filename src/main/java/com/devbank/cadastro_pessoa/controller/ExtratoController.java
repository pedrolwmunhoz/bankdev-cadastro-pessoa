package com.devbank.cadastro_pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devbank.cadastro_pessoa.models.HistoricoMovimentacoes;
import com.devbank.cadastro_pessoa.models.Pessoa;
import com.devbank.cadastro_pessoa.models.Saldo;
import com.devbank.cadastro_pessoa.repositories.PessoaRepository;
import com.devbank.cadastro_pessoa.repositories.SaldoRepository;
import com.devbank.cadastro_pessoa.services.ExtratoService;

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

    // Método para obter o extrato dos últimos 30 dias
    @GetMapping("/detalhado")
    public ResponseEntity<List<HistoricoMovimentacoes>> obterExtratoUltimos30Dias(@RequestParam Integer idPessoa) {
        // Verifica se a pessoa existe no banco
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(idPessoa);
        
        if (optionalPessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Pessoa pessoa = optionalPessoa.get();

        // Verifica se o saldo associado à pessoa existe
        Optional<Saldo> optionalSaldo = saldoRepository.findByPessoa(pessoa);
        if (optionalSaldo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Saldo saldo = optionalSaldo.get();
        List<HistoricoMovimentacoes> extrato = extratoService.obterExtratoUltimos30Dias(saldo);
        return ResponseEntity.ok(extrato);
    }

    // Método para obter o saldo pelo ID da pessoa
    @GetMapping("/saldo")
    public ResponseEntity<Object> obterSaldoPorIdPessoa(@RequestParam Integer idPessoa) {
        // Buscar a pessoa pelo ID
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(idPessoa);

        if (optionalPessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        Pessoa pessoa = optionalPessoa.get();

        // Buscar o saldo pela pessoa
        Optional<Saldo> optionalSaldo = saldoRepository.findByPessoa(pessoa);

        if (optionalSaldo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Saldo não encontrado para a pessoa fornecida");
        }

        Saldo saldo = optionalSaldo.get();
        return ResponseEntity.ok(saldo);
    }
}
