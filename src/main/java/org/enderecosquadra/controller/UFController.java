package org.enderecosquadra.controller;

import jakarta.validation.Valid;
import org.enderecosquadra.domain.uf.UF;
import org.enderecosquadra.domain.uf.UFRequestDTO;
import org.enderecosquadra.domain.uf.UFRequestPutDTO;
import org.enderecosquadra.services.UFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        List<UF> estados = ufService.buscarUfComParametros(codigoUF, sigla, nome, status);

        if(estados.size() == 1) {
            return ResponseEntity.ok(estados.getFirst());
        }
        return ResponseEntity.ok(estados);
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
