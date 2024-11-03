package com.bankdev.cadastro_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankdev.cadastro_pessoa.models.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {
    boolean existsByCnpj(String cnpj);
}