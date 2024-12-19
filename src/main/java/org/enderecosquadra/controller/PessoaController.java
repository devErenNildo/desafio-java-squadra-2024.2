package org.enderecosquadra.controller;

import org.enderecosquadra.domain.pessoa.PessoaRequestDTO;
import org.enderecosquadra.domain.pessoa.PessoaRequestPutDTO;
import org.enderecosquadra.domain.pessoa.PessoaResponseDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.services.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            if(codigoPessoa != null){
                return ResponseEntity.ok(pessoaService.buscarPessoaDetalhada(codigoPessoa));
            }
            return ResponseEntity.ok(pessoaService.buscarPessoa(null, login, status));

        }catch (NoSuchElementException e){
            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception e){
            throw new ExceptionDeRetorno("Aconteceu um erro inesperado, tente novamente");
        }
    }

    @PostMapping
    public ResponseEntity<List<PessoaResponseDTO>> adicionarPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.ok(pessoaService.adicionarPessoa(pessoaRequestDTO));
    }

    @PutMapping
    public ResponseEntity<List<PessoaResponseDTO>> editarPessoa(@RequestBody PessoaRequestPutDTO pessoaRequestPutDTO){
        return ResponseEntity.ok(pessoaService.editarPessoa(pessoaRequestPutDTO));
    }
}
