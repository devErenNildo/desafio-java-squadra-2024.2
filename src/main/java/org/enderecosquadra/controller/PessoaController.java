package org.enderecosquadra.controller;

import org.enderecosquadra.domain.pessoa.PessoaRequestDTO;
import org.enderecosquadra.domain.pessoa.PessoaResponseDTO;
import org.enderecosquadra.services.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<?> buscarPessoa(
            @RequestParam(required = false) Long codigoPessoa,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) Integer status
    ) {
        if(codigoPessoa != null){
            return ResponseEntity.ok(pessoaService.buscarPessoaDetalhada(codigoPessoa));
        }
        return ResponseEntity.ok(pessoaService.buscarPessoa(null, login, status));
    }

    @PostMapping
    public ResponseEntity<List<PessoaResponseDTO>> adicionarPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.ok(pessoaService.adicionarPessoa(pessoaRequestDTO));
    }
}
