package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.crosscutting.ContaAtualizadaDTO;
import com.dbserver.treinamentospring.crosscutting.ContaDTO;
import com.dbserver.treinamentospring.servico.IContaService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/contas")
public class ContaController {

  private final IContaService contaService;

  public ContaController(
      IContaService contaService) {
    this.contaService = contaService;
  }

  @PostMapping()
  public ResponseEntity<Void> criarConta(@RequestBody ContaDTO contaDTO) {
    this.contaService.criarConta(contaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping()
  public ResponseEntity<Void> atualizarConta(@RequestBody ContaAtualizadaDTO contaAtualizadaDTO) {
    this.contaService.atualizarConta(contaAtualizadaDTO);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping()
  public ResponseEntity<Void> excluirConta(@RequestParam @NotNull Long idConta) {
    this.contaService.excluirConta(idConta);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
