package org.enderecosquadra.controller;

import jakarta.validation.Valid;
import org.enderecosquadra.domain.municipio.MunicipioRequestDTO;
import org.enderecosquadra.domain.municipio.MunicipioRequestPutDTO;
import org.enderecosquadra.domain.municipio.MunicipioResponseDTO;
import org.enderecosquadra.services.municipio.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        List<MunicipioResponseDTO> municipios = municipioService.buscarMunicipioComParametros(codigoMunicipio, codigoUF, nome, status);

        if(municipios.size() == 1){
            return ResponseEntity.ok(municipios.getFirst());
        }

        return ResponseEntity.ok(municipios);
    }


    // ADICIONANDO MUNICIPIO
    @PostMapping
    public ResponseEntity<List<MunicipioResponseDTO>> adicionarMunicipio(@RequestBody @Valid MunicipioRequestDTO municipioRequestDTO) {
        return ResponseEntity.ok(municipioService.adicionarMunicipio(municipioRequestDTO));
    }

    @PutMapping
    public ResponseEntity<List<MunicipioResponseDTO>> editarMunicipio(@RequestBody @Valid MunicipioRequestPutDTO municipioRequestDTO){
        return ResponseEntity.ok(municipioService.editarMunicipio(municipioRequestDTO));
    }
}
