package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.servico.IContaConsultaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/contas")
public class ContaConsultaController {

  private final IContaConsultaService contaConsultaService;

  public ContaConsultaController(
      IContaConsultaService contaConsultaService) {
    this.contaConsultaService = contaConsultaService;
  }

  @GetMapping()
  public ResponseEntity<List<Conta>> obterTodasAsContas() {
    return ResponseEntity.ok(this.contaConsultaService.obterTodasAsContas());
  }

  @GetMapping("/{idConta}")
  public ResponseEntity<Conta> obterTodasAsContas(@PathVariable Long idConta) {
    return ResponseEntity.ok(this.contaConsultaService.obterConta(idConta));
  }

}
