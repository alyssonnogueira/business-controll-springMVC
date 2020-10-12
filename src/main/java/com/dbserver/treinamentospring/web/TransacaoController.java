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
import org.springframework.web.bind.annotation.PathVariable;
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
    this.transacaoService.criarTransferencia(despesaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/receita")
  public ResponseEntity<Void> criarTransacao(@RequestBody ReceitaDTO receitaDTO) {
    this.transacaoService.criarTransferencia(receitaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/transferencia")
  public ResponseEntity<Void> criarTransacao(@RequestBody TransferenciaDTO transferenciaDTO) {
    this.transacaoService.criarTransferencia(transferenciaDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{idTransacao}")
  public ResponseEntity<Void> excluirTransacao(@PathVariable @NotNull Long idTransacao) {
    this.transacaoService.excluirTransacao(idTransacao);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
