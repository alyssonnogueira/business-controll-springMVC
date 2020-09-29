package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.crosscutting.DespesaDTO;
import com.dbserver.treinamentospring.crosscutting.ReceitaDTO;
import com.dbserver.treinamentospring.crosscutting.TransferenciaDTO;
import com.dbserver.treinamentospring.servico.ITransacaoService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/transacoes")
public class TransacaoController {

  private final ITransacaoService transacaoService;

  public TransacaoController(ITransacaoService transacaoService) {
    this.transacaoService = transacaoService;
  }

  @PostMapping("/despesa")
  public ResponseEntity<Void> criarTransacao(@RequestBody DespesaDTO despesaDTO) {
    this.transacaoService.criarTransacao(despesaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/receita")
  public ResponseEntity<Void> criarTransacao(@RequestBody ReceitaDTO receitaDTO) {
    this.transacaoService.criarTransacao(receitaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/transferencia")
  public ResponseEntity<Void> criarTransacao(@RequestBody TransferenciaDTO transferenciaDTO) {
    this.transacaoService.criarTransacao(transferenciaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping()
  public ResponseEntity<Void> excluirTransacao(@RequestParam @NotNull Long idConta) {
    this.transacaoService.excluirTransacao(idConta);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
