package com.devbank.cadastro_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devbank.cadastro_pessoa.models.HistoricoMovimentacoes;
import com.devbank.cadastro_pessoa.models.Saldo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricoMovimentacoesRepository extends JpaRepository<HistoricoMovimentacoes, Integer> {
    List<HistoricoMovimentacoes> findBySaldoAndDataHoraBetween(Saldo saldo, LocalDateTime dataInicio, LocalDateTime dataFim);
}