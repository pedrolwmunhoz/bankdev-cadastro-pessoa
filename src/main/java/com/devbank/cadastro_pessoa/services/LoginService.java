package com.devbank.cadastro_pessoa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbank.cadastro_pessoa.dto.PessoaFisicaDTO;
import com.devbank.cadastro_pessoa.dto.PessoaJuridicaDTO;
import com.devbank.cadastro_pessoa.dto.PessoaLoginResponseDTO;
import com.devbank.cadastro_pessoa.dto.SaldoDTO;
import com.devbank.cadastro_pessoa.models.HistoricoLogin;
import com.devbank.cadastro_pessoa.models.Login;
import com.devbank.cadastro_pessoa.models.Pessoa;
import com.devbank.cadastro_pessoa.models.PessoaFisica;
import com.devbank.cadastro_pessoa.models.PessoaJuridica;
import com.devbank.cadastro_pessoa.models.Saldo;
import com.devbank.cadastro_pessoa.repositories.HistoricoLoginRepository;
import com.devbank.cadastro_pessoa.repositories.LoginRepository;
import com.devbank.cadastro_pessoa.repositories.PessoaFisicaRepository;
import com.devbank.cadastro_pessoa.repositories.PessoaJuridicaRepository;
import com.devbank.cadastro_pessoa.repositories.PessoaRepository;
import com.devbank.cadastro_pessoa.repositories.SaldoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private SaldoRepository saldoRepository;

    @Autowired
    private HistoricoLoginRepository historicoLoginRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaLoginResponseDTO validarLogin(String email, String senha) {
        Optional<Login> loginOptional = loginRepository.findByEmail(email);
        boolean isValid = false;
        PessoaLoginResponseDTO pessoaLoginResponseDTO = new PessoaLoginResponseDTO();

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
                            pessoaLoginResponseDTO.setPessoaFisicaDTO(pessoaFisicaDTO);
                            pessoaLoginResponseDTO.setIdPessoa(pf.getIdPessoa());

                            Optional <Saldo> optionalSaldo = saldoRepository.findByPessoa(pf);
                            if(optionalSaldo.isPresent()){
                                Saldo saldo = optionalSaldo.get();
                                pessoaLoginResponseDTO.setSaldoDTO(new SaldoDTO());
                                pessoaLoginResponseDTO.getSaldoDTO().setSaldoAtual(saldo.getSaldoAtual());
                                pessoaLoginResponseDTO.getSaldoDTO().setLimiteCredito(saldo.getLimiteCredito());
                                pessoaLoginResponseDTO.getSaldoDTO().setDataUltimaAtualizacao(LocalDate.now());
                            }
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
                            pessoaLoginResponseDTO.setPessoaJuridicaDTO(pessoaJuridicaDTO);
                            pessoaLoginResponseDTO.setIdPessoa(pj.getIdPessoa());

                            Optional <Saldo> optionalSaldo = saldoRepository.findByPessoa(pj);
                            if(optionalSaldo.isPresent()){
                                Saldo saldo = optionalSaldo.get();
                                pessoaLoginResponseDTO.setSaldoDTO(new SaldoDTO());
                                pessoaLoginResponseDTO.getSaldoDTO().setSaldoAtual(saldo.getSaldoAtual());
                                pessoaLoginResponseDTO.getSaldoDTO().setLimiteCredito(saldo.getLimiteCredito());
                                pessoaLoginResponseDTO.getSaldoDTO().setDataUltimaAtualizacao(LocalDate.now());
                            }
                        });
                    }
                }
            }
        }

        cadastrarHistoricoLogin(loginOptional, isValid);
        return pessoaLoginResponseDTO;
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
