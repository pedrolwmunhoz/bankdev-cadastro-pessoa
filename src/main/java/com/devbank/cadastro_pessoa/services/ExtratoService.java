package com.devbank.cadastro_pessoa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbank.cadastro_pessoa.models.HistoricoMovimentacoes;
import com.devbank.cadastro_pessoa.models.Saldo;
import com.devbank.cadastro_pessoa.repositories.HistoricoMovimentacoesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExtratoService {

    @Autowired
    private HistoricoMovimentacoesRepository historicoRepository;

    public List<HistoricoMovimentacoes> obterExtratoUltimos30Dias(Saldo saldo) {
        LocalDateTime dataFim = LocalDateTime.now();
        LocalDateTime dataInicio = dataFim.minusDays(30);
        return historicoRepository.findBySaldoAndDataHoraBetween(saldo, dataInicio, dataFim);
    }
}