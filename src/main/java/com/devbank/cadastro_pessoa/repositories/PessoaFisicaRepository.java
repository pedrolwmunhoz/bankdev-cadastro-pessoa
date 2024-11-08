package com.devbank.cadastro_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devbank.cadastro_pessoa.models.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {
    boolean existsByCpf(String cpf);
}
