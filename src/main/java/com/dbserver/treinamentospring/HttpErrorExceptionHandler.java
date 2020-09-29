package com.dbserver.treinamentospring;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HttpErrorExceptionHandler {

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseBody
  public ResponseEntity<String> tratarErroRecursoNaoEncontrado(RuntimeException e) {
    return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
  }
}
