package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.crosscutting.ResponsavelAtualizadoDTO;
import com.dbserver.treinamentospring.crosscutting.ResponsavelDTO;
import com.dbserver.treinamentospring.servico.IResponsavelService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("/v1/responsaveis")
public class ResponsavelController {

  private final IResponsavelService responsavelService;

  public ResponsavelController(
      IResponsavelService responsavelService) {
    this.responsavelService = responsavelService;
  }

  @PostMapping()
  public ResponseEntity<Void> criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO) {
    this.responsavelService.criarResponsavel(responsavelDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping()
  public ResponseEntity<Void> atualizarResponsavel(@RequestBody ResponsavelAtualizadoDTO responsavelAtualizadoDTO) {
    this.responsavelService.atualizarResponsavel(responsavelAtualizadoDTO);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{idResponsavel}")
  public ResponseEntity<Void> excluirResponsavel(@PathVariable @NotNull Long idResponsavel) {
    this.responsavelService.excluirResponsavel(idResponsavel);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
