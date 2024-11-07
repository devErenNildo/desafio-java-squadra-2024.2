package org.enderecosquadra.exceptions.handler;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.enderecosquadra.exceptions.dto.ExceptionDeRetornoDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CampoExistenteExceptionHandler {


//  EXCEPTION PARA TRATAR CAMPOS REPETIDOS NO BANCO DE DADOS
    @ExceptionHandler(ExceptionDeRetorno.class)
    public ResponseEntity<ExceptionDeRetornoDTO> campoRepetido(ExceptionDeRetorno err){
        ExceptionDeRetornoDTO exceptionDTO = new ExceptionDeRetornoDTO(err.getMensagem(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

//  EXCEPTION PARA TRATAR CAMPOS VAZIOS NO CONTROLLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDeRetornoDTO> campoVazio(MethodArgumentNotValidException ex){

        // SALVA O A MENSAGEM DE ERRO DOS VALIDATIONS
        String msg = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();

        // CRIA UMA EXCEPTION COM A MENSAGEM E O STATUS
        ExceptionDeRetornoDTO exceptionDTO = new ExceptionDeRetornoDTO(msg, HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDeRetornoDTO> erroNoJson(HttpMessageNotReadableException ex){
        String msg = "Erro na leitura do JSON: Verifique a estrutura e a formatação da requisição.";

        ExceptionDeRetornoDTO exceptionDTO = new ExceptionDeRetornoDTO(msg, HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }
}
