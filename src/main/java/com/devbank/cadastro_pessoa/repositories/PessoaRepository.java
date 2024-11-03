package com.devbank.cadastro_pessoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devbank.cadastro_pessoa.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    Optional<Pessoa> findByEmail(String email);
}
