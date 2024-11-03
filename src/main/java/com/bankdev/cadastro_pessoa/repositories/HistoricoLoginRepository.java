package com.bankdev.cadastro_pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankdev.cadastro_pessoa.models.HistoricoLogin;

@Repository
public interface HistoricoLoginRepository extends JpaRepository<HistoricoLogin, Integer> {
}
