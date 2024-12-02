package org.enderecosquadra.controller;

import jakarta.validation.Valid;
import org.enderecosquadra.domain.bairro.BairroRequestDTO;
import org.enderecosquadra.domain.bairro.BairroRequestPutDTO;
import org.enderecosquadra.domain.bairro.BairroResponseDTO;
import org.enderecosquadra.services.bairro.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    // BUSCAR BAIRRO ============================================================================
    @GetMapping
    public ResponseEntity<?> buscarBairroComParametros(
            @RequestParam(required = false) Long codigoBairro,
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status
    ) {
        List<BairroResponseDTO> bairros = bairroService.buscarBairroComParametros(
                codigoBairro,
                codigoMunicipio,
                nome,
                status
        );

        if (codigoBairro != null) {
            return ResponseEntity.ok(bairros.getFirst());
        }else {
            return ResponseEntity.ok(bairros);
        }
    }

    // ADICIONAR BAIRRO =============================================================================
    @PostMapping
    public ResponseEntity<List<BairroResponseDTO>> adicionarBairro(@RequestBody @Valid BairroRequestDTO bairroRequestDTO){
        return ResponseEntity.ok(bairroService.adicionarBairro(bairroRequestDTO));
    }

    @PutMapping
    public ResponseEntity<List<BairroResponseDTO>> editarBairro(@RequestBody @Valid BairroRequestPutDTO bairroRequestPutDTO){
        return ResponseEntity.ok(bairroService.editarBairro(bairroRequestPutDTO));
    }
}
