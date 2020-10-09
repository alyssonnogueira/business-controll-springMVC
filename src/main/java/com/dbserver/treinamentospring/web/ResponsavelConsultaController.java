package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.servico.IResponsavelConsultaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/v1/responsaveis")
public class ResponsavelConsultaController {

  private final IResponsavelConsultaService responsavelConsultaService;

  public ResponsavelConsultaController(
      IResponsavelConsultaService responsavelConsultaService) {
    this.responsavelConsultaService = responsavelConsultaService;
  }

  @GetMapping()
  public ResponseEntity<List<Responsavel>> obterTodosResponsaveis() {
    return ResponseEntity.ok(
        this.responsavelConsultaService.obterTodosOsResponsaveis()
    );
  }

  @GetMapping("/{idResponsavel}")
  public ResponseEntity<Responsavel> obterResponsavel(@PathVariable Long idResponsavel) {
    return ResponseEntity.ok(
        this.responsavelConsultaService.obterResponsavel(idResponsavel)
    );
  }

}
