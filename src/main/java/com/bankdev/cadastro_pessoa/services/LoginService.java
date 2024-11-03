package com.bankdev.cadastro_pessoa.services;

import com.bankdev.cadastro_pessoa.models.HistoricoLogin;
import com.bankdev.cadastro_pessoa.models.Login;
import com.bankdev.cadastro_pessoa.repositories.HistoricoLoginRepository;
import com.bankdev.cadastro_pessoa.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private HistoricoLoginRepository historicoLoginRepository;

    public boolean validarLogin(String email, String senha) {
        Optional<Login> loginOptional = loginRepository.findByEmail(email);
        boolean isValid = false;
        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            isValid = login.getSenha().equals(senha);
        }
        cadastrarHistoricoLogin(loginOptional, isValid);
        return isValid;
    }

    private void cadastrarHistoricoLogin(Optional<Login> loginOptional, boolean isValid) {
        HistoricoLogin historicoLogin = new HistoricoLogin();
        historicoLogin.setData(LocalDate.now());
        historicoLogin.setHora(LocalTime.now());
        historicoLogin.setDescricao(isValid ? "Login bem-sucedido" : "Tentativa de login falhou");
        loginOptional.ifPresent(historicoLogin::setLogin);
        historicoLoginRepository.save(historicoLogin);
    }
}