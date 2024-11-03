package com.bankdev.cadastro_pessoa.repositories;

import com.bankdev.cadastro_pessoa.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByEmail(String email);
}