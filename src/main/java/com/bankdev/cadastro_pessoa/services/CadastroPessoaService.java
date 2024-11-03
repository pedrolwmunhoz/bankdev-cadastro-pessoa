package com.bankdev.cadastro_pessoa.services;

import com.bankdev.cadastro_pessoa.dto.PessoaCadastroDTO;
import com.bankdev.cadastro_pessoa.dto.PessoaFisicaDTO;
import com.bankdev.cadastro_pessoa.dto.PessoaJuridicaDTO;
import com.bankdev.cadastro_pessoa.models.Pessoa;
import com.bankdev.cadastro_pessoa.models.PessoaFisica;
import com.bankdev.cadastro_pessoa.models.PessoaJuridica;
import com.bankdev.cadastro_pessoa.models.Login;
import com.bankdev.cadastro_pessoa.models.Endereco;
import com.bankdev.cadastro_pessoa.models.Saldo;
import com.bankdev.cadastro_pessoa.repositories.LoginRepository;
import com.bankdev.cadastro_pessoa.repositories.EnderecoRepository;
import com.bankdev.cadastro_pessoa.repositories.SaldoRepository;
import com.bankdev.cadastro_pessoa.repositories.PessoaFisicaRepository;
import com.bankdev.cadastro_pessoa.repositories.PessoaJuridicaRepository;
import com.bankdev.cadastro_pessoa.repositories.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private SaldoRepository saldoRepository;

    public void cadastrarPessoa(PessoaCadastroDTO pessoaCadastroDTO) {
        String tipo = pessoaCadastroDTO.getPessoa().getTipo();

        // Verificar se o email já existe
        String email = pessoaCadastroDTO.getPessoa().getEmail();
        if (pessoaRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        // Verificar se o número de telefone já existe
        String telefone = pessoaCadastroDTO.getPessoa().getTelefone();
        if (pessoaRepository.existsByTelefone(telefone)) {
            throw new IllegalArgumentException("Número de telefone já cadastrado");
        }

        if ("FISICA".equalsIgnoreCase(tipo)) {
            PessoaFisicaDTO pf = pessoaCadastroDTO.getPessoaFisicaDTO();
            String cpf = pf.getCpf();
            if (pessoaFisicaRepository.existsByCpf(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }

            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setNumeroConta("4004");
            pessoaFisica.setTelefone(pessoaCadastroDTO.getPessoa().getTelefone());
            pessoaFisica.setTipo(tipo);
            pessoaFisica.setNome(pf.getNome());
            pessoaFisica.setIdade(pf.getIdade());
            pessoaFisica.setCpf(cpf);
            pessoaFisica.setEmail(pessoaCadastroDTO.getPessoa().getEmail());
            PessoaFisica pessoaFisicaSalva = pessoaFisicaRepository.save(pessoaFisica);
            cadastrarInformacoesComplementares(pessoaCadastroDTO, pessoaFisicaSalva);

        } else if ("JURIDICA".equalsIgnoreCase(tipo)) {
            PessoaJuridicaDTO pj = pessoaCadastroDTO.getPessoaJuridicaDTO();
            String cnpj = pj.getCnpj();
            if (pessoaJuridicaRepository.existsByCnpj(cnpj)) {
                throw new IllegalArgumentException("CNPJ já cadastrado");
            }

            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setNumeroConta("4004");
            pessoaJuridica.setTelefone(pessoaCadastroDTO.getPessoa().getTelefone());
            pessoaJuridica.setTipo(tipo);
            pessoaJuridica.setRazaoSocial(pj.getRazaoSocial());
            pessoaJuridica.setNomeFantasia(pj.getNomeFantasia());
            pessoaJuridica.setCnpj(cnpj);
            pessoaJuridica.setEmail(pessoaCadastroDTO.getPessoa().getEmail());
            PessoaJuridica pessoaJuridicaSalva = pessoaJuridicaRepository.save(pessoaJuridica);
            cadastrarInformacoesComplementares(pessoaCadastroDTO, pessoaJuridicaSalva);
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido. Deve ser 'FISICA' ou 'JURIDICA'.");
        }
    }

    private void cadastrarInformacoesComplementares(PessoaCadastroDTO pessoaCadastroDTO, Pessoa pessoa) {

        pessoa.setNumeroConta("4004" + pessoa.getIdPessoa());

        // Persistir Login
        Login login = new Login();
        login.setEmail(pessoaCadastroDTO.getLogin().getEmail());
        login.setSenha(pessoaCadastroDTO.getLogin().getSenha());
        login.setPessoa(pessoa);
        loginRepository.save(login);

        // Persistir Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro(pessoaCadastroDTO.getEndereco().getLogradouro());
        endereco.setNumero(pessoaCadastroDTO.getEndereco().getNumero());
        endereco.setComplemento(pessoaCadastroDTO.getEndereco().getComplemento());
        endereco.setBairro(pessoaCadastroDTO.getEndereco().getBairro());
        endereco.setCidade(pessoaCadastroDTO.getEndereco().getCidade());
        endereco.setEstado(pessoaCadastroDTO.getEndereco().getEstado());
        endereco.setCep(pessoaCadastroDTO.getEndereco().getCep());
        endereco.setPessoa(pessoa);
        enderecoRepository.save(endereco);

        // Persistir Saldo
        Saldo saldo = new Saldo();
        saldo.setSaldoAtual(pessoaCadastroDTO.getSaldo().getSaldoAtual());
        saldo.setLimiteCredito(pessoaCadastroDTO.getSaldo().getLimiteCredito());
        saldo.setDataUltimaAtualizacao(pessoaCadastroDTO.getSaldo().getDataUltimaAtualizacao());
        saldo.setPessoa(pessoa);
        saldoRepository.save(saldo);
    }
}
