package com.devbank.cadastro_pessoa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbank.cadastro_pessoa.dto.PessoaFisicaDTO;
import com.devbank.cadastro_pessoa.dto.PessoaJuridicaDTO;
import com.devbank.cadastro_pessoa.dto.PessoaLoginReturnDTO;
import com.devbank.cadastro_pessoa.models.HistoricoLogin;
import com.devbank.cadastro_pessoa.models.Login;
import com.devbank.cadastro_pessoa.models.Pessoa;
import com.devbank.cadastro_pessoa.models.PessoaFisica;
import com.devbank.cadastro_pessoa.models.PessoaJuridica;
import com.devbank.cadastro_pessoa.repositories.HistoricoLoginRepository;
import com.devbank.cadastro_pessoa.repositories.LoginRepository;
import com.devbank.cadastro_pessoa.repositories.PessoaFisicaRepository;
import com.devbank.cadastro_pessoa.repositories.PessoaJuridicaRepository;
import com.devbank.cadastro_pessoa.repositories.PessoaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private HistoricoLoginRepository historicoLoginRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaLoginReturnDTO validarLogin(String email, String senha) {
        Optional<Login> loginOptional = loginRepository.findByEmail(email);
        boolean isValid = false;
        PessoaLoginReturnDTO pessoaLoginReturnDTO = new PessoaLoginReturnDTO();

        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            isValid = login.getSenha().equals(senha);
            if (isValid) {
                // Busca a pessoa associada ao login pelo email
                Optional<Pessoa> pessoaOptional = pessoaRepository.findByEmail(email);
                if (pessoaOptional.isPresent()) {
                    Pessoa pessoa = pessoaOptional.get();
                    if ("FISICA".equalsIgnoreCase(pessoa.getTipo())) {
                        Optional<PessoaFisica> pessoaFisicaOptional = pessoaFisicaRepository.findById(pessoa.getIdPessoa());
                        pessoaFisicaOptional.ifPresent(pf -> {
                            PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
                            pessoaFisicaDTO.setNome(pf.getNome());
                            pessoaFisicaDTO.setIdade(pf.getIdade());
                            pessoaFisicaDTO.setCpf(pf.getCpf());
                            pessoaFisicaDTO.setEmail(pf.getEmail());
                            pessoaFisicaDTO.setTipo(pf.getTipo());
                            pessoaFisicaDTO.setTelefone(pf.getTelefone());
                            pessoaLoginReturnDTO.setPessoaFisicaDTO(pessoaFisicaDTO);
                        });
                    } else if ("JURIDICA".equalsIgnoreCase(pessoa.getTipo())) {
                        Optional<PessoaJuridica> pessoaJuridicaOptional = pessoaJuridicaRepository.findById(pessoa.getIdPessoa());
                        pessoaJuridicaOptional.ifPresent(pj -> {
                            PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
                            pessoaJuridicaDTO.setRazaoSocial(pj.getRazaoSocial());
                            pessoaJuridicaDTO.setNomeFantasia(pj.getNomeFantasia());
                            pessoaJuridicaDTO.setCnpj(pj.getCnpj());
                            pessoaJuridicaDTO.setEmail(pj.getEmail());
                            pessoaJuridicaDTO.setTipo(pj.getTipo());
                            pessoaJuridicaDTO.setTelefone(pj.getTelefone());
                            pessoaLoginReturnDTO.setPessoaJuridicaDTO(pessoaJuridicaDTO);
                        });
                    }
                }
            }
        }

        cadastrarHistoricoLogin(loginOptional, isValid);
        return pessoaLoginReturnDTO;
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