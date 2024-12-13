package org.enderecosquadra.controller;

import jakarta.validation.Valid;
import org.enderecosquadra.domain.municipio.MunicipioRequestDTO;
import org.enderecosquadra.domain.municipio.MunicipioRequestPutDTO;
import org.enderecosquadra.domain.municipio.MunicipioResponseDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.services.municipio.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        try {
            nome = Optional.ofNullable(nome).map(String::toUpperCase).orElse(null);

            List<MunicipioResponseDTO> municipios = municipioService.buscarMunicipioComParametros(codigoMunicipio, codigoUF, nome, status);

            if(codigoMunicipio != null){
                return ResponseEntity.ok(municipios.getFirst());
            }

            return ResponseEntity.ok(municipios);
        }catch (NoSuchElementException e){
            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception e){
            throw new ExceptionDeRetorno("Aconteceu um erro inesperado, tente novamente");
        }

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
