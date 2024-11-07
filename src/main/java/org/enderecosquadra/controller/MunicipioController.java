package org.enderecosquadra.controller;

import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<?> buscarMunicipioComParametros(
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) Long codigoUF,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status
    ) {

        List<Municipio> municipios = municipioService.buscarMunicipioComParametros(codigoMunicipio, codigoUF, nome, status);

        if(municipios.size() == 1){
            return ResponseEntity.ok(municipios.getFirst());
        }

        return ResponseEntity.ok(municipios);
    }
}
