package org.enderecosquadra.controller;

import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> buscarBairroComParametros(
            @RequestParam(required = false) Long codigoBairro,
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status
    ) {
        List<Bairro> bairros = bairroService.buscarBairroComParametros(codigoBairro, codigoMunicipio, nome, status);

        if(bairros.size() == 1)
            return ResponseEntity.ok(bairros.getFirst());

        return ResponseEntity.ok(bairros);
    }
}
