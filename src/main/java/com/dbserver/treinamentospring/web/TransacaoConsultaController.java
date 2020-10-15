package com.dbserver.treinamentospring.web;

import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.servico.ITransacaoConsultaService;
import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/transacoes")
public class TransacaoConsultaController {

  private final ITransacaoConsultaService transacaoConsultaService;

  public TransacaoConsultaController(ITransacaoConsultaService transacaoConsultaService) {
    this.transacaoConsultaService = transacaoConsultaService;
  }

  @GetMapping()
  public ResponseEntity<List<Transacao>> obterTodasAsTransacoes() {
    return ResponseEntity.ok(this.transacaoConsultaService.obterTodasTransacoes());
  }

  @GetMapping("paginado")
  public ResponseEntity<Page<Transacao>> obterTodasAsTransacoesComFiltro(
      final @RequestParam(required = false) String descricaoCategoria,
      final @RequestParam(required = false) List<Long> idsResponsaveis,
      final @RequestParam(required = false) List<Long> idsContas,
      final @RequestParam(required = false) List<TipoTransacaoEnum> tiposTransacao,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
      final @RequestParam(required = false) LocalDate dataInicial,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
      final @RequestParam(required = false) LocalDate dataFinal,
      final @RequestParam(required = false, defaultValue = "0") Integer pagina,
      final @RequestParam(required = false, defaultValue = "20") Integer limite,
      final @RequestParam(required = false, defaultValue = "DESC") Direction ordem,
      final @RequestParam(required = false, defaultValue = "data") String
              colunaDeOrdenacao) {

    LocalDateTime dataInicialDateTime = dataInicial != null ? dataInicial.atStartOfDay() : null;
    LocalDateTime dataFinalDateTime = dataFinal != null ? dataFinal.atStartOfDay() : null;
    return ResponseEntity.ok(
        this.transacaoConsultaService.obterTodasTransacoes(
            descricaoCategoria,
            idsResponsaveis,
            idsContas,
            tiposTransacao,
            dataInicialDateTime,
            dataFinalDateTime,
            PageRequest.of(pagina, limite, ordem, colunaDeOrdenacao)));
  }

  @GetMapping("/{idTransacao}")
  public ResponseEntity<Transacao> obterTransacao(@PathVariable Long idTransacao) {
    return ResponseEntity.ok(this.transacaoConsultaService.obterTransacao(idTransacao));
  }
}
