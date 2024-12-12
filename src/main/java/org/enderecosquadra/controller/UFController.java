package org.enderecosquadra.controller;

import jakarta.validation.Valid;
import org.enderecosquadra.domain.uf.UF;
import org.enderecosquadra.domain.uf.UFRequestDTO;
import org.enderecosquadra.domain.uf.UFRequestPutDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.services.uf.UFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/uf")
public class UFController {
    @Autowired
    private UFService ufService;

//  buscar estados
    @GetMapping
    public ResponseEntity<?> retornarUfComParametros(
            @RequestParam(required = false) Long codigoUF,
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status
    ) {
        try {
            sigla = Optional.ofNullable(sigla).map(String::toUpperCase).orElse(null);
            nome = Optional.ofNullable(nome).map(String::toUpperCase).orElse(null);

            List<UF> estados = ufService.buscarUfComParametros(codigoUF, sigla, nome, status);

            if(status != null && codigoUF == null && sigla == null && nome == null) {
                return ResponseEntity.ok(estados);
            }
            if(codigoUF != null || sigla != null || nome != null){
                return ResponseEntity.ok(estados.getFirst());
            }

            return ResponseEntity.ok(estados);

        } catch (NoSuchElementException e){
            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception e){
            throw new ExceptionDeRetorno("Aconteceu um erro inesperado, tente novamente");
        }
    }

//  adicionar estado
    @PostMapping
    public ResponseEntity<List<UF>> adicionarUf(@RequestBody @Valid UFRequestDTO uf) {
        return  ResponseEntity.ok(ufService.adicionarUf(uf));
    }

    @PutMapping
    public ResponseEntity<List<UF>> atualizarUf(@RequestBody @Valid UFRequestPutDTO uf) {
        return ResponseEntity.ok(ufService.editarUf(uf));
    }

}
