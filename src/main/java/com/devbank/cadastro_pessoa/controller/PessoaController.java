package com.devbank.cadastro_pessoa.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devbank.cadastro_pessoa.dto.PessoaCadastroDTO;
import com.devbank.cadastro_pessoa.services.CadastroPessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private CadastroPessoaService cadastroPessoaService;

    @GetMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPessoa(@RequestBody @Valid PessoaCadastroDTO pssoaCadastroDTO ) {
        try {

            // // Simulação de teste interno
            // PessoaCadastroDTO mockPessoaCadastroDTO = new PessoaCadastroDTO();
            // mockPessoaCadastroDTO.setPessoa(new PessoaDTO());
            // mockPessoaCadastroDTO.getPessoa().setTipo("FISICA");
            // mockPessoaCadastroDTO.getPessoa().setTelefone("1438584242");
            // mockPessoaCadastroDTO.getPessoa().setEmail("pedro2.silva@example.com");

            // PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
            // pessoaFisicaDTO.setNome("Pedro Munhoz");
            // pessoaFisicaDTO.setIdade(30);
            // pessoaFisicaDTO.setCpf("145154213");

            // mockPessoaCadastroDTO.setPessoaFisicaDTO(pessoaFisicaDTO);

            // // PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
            // // pessoaJuridicaDTO.setNomeFantasia("Empresa XYZ");
            // // pessoaJuridicaDTO.setCnpj("12345678000199");
            // // pessoaJuridicaDTO.setRazaoSocial("Empresa XYZ Ltda");
            // // pessoaJuridicaDTO.setEmail("contato@empresa.xyz");
            // // mockPessoaCadastroDTO.setPessoaJuridicaDTO(pessoaJuridicaDTO);

            // LoginDTO loginDTO = new LoginDTO();
            // loginDTO.setEmail("pedro2.silva@example.com");
            // loginDTO.setSenha("senhaSegura123");
            // mockPessoaCadastroDTO.setLogin(loginDTO);

            // EnderecoDTO enderecoDTO = new EnderecoDTO();
            // enderecoDTO.setLogradouro("Rua das Flores");
            // enderecoDTO.setNumero("123");
            // enderecoDTO.setBairro("Centro");
            // enderecoDTO.setCidade("São Paulo");
            // enderecoDTO.setEstado("SP");
            // enderecoDTO.setCep("12345678");
            // mockPessoaCadastroDTO.setEndereco(enderecoDTO);

            // SaldoDTO saldoDTO = new SaldoDTO();
            // saldoDTO.setSaldoAtual(new BigDecimal("1000.00"));
            // saldoDTO.setLimiteCredito(new BigDecimal("500.00"));
            // saldoDTO.setDataUltimaAtualizacao(LocalDate.now());
            // mockPessoaCadastroDTO.setSaldo(saldoDTO);

            cadastroPessoaService.cadastrarPessoa(pssoaCadastroDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
