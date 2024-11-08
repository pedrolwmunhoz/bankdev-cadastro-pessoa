package com.devbank.cadastro_pessoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devbank.cadastro_pessoa.models.Pessoa;
import com.devbank.cadastro_pessoa.models.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer> {
     Optional<Saldo> findByPessoa(Pessoa pessoa);
}
