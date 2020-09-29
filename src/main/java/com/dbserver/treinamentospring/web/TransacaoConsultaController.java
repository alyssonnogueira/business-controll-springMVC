package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.servico.ITransacaoConsultaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/transacoes")
public class TransacaoConsultaController {

  private final ITransacaoConsultaService transacaoConsultaService;

  public TransacaoConsultaController(ITransacaoConsultaService transacaoConsultaService) {
    this.transacaoConsultaService = transacaoConsultaService;
  }

  @GetMapping()
  public ResponseEntity<List<Transacao>> obterTodasAsContas() {
    return ResponseEntity.ok(this.transacaoConsultaService.obterTodasTransacoes());
  }

  @GetMapping("/{idTransferencia}")
  public ResponseEntity<Transacao> obterTransferencia(@PathVariable Long idTransferencia) {
    return ResponseEntity.ok(this.transacaoConsultaService.obterTransacao(idTransferencia));
  }

}
