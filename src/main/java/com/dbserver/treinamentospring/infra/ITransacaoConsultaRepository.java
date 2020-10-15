package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransacaoConsultaRepository {

  Transacao obterTransacao(Long idTransacao);

  List<Transacao> obterTodasTransacoes();

  Page<Transacao> obterTodasTransacoes(
      final String descricaoCategoria,
      final List<Long> idsResponsaveis,
      final List<Long> idsContas,
      final List<TipoTransacaoEnum> tiposTransacao,
      final LocalDateTime dataInicial,
      final LocalDateTime dataFinal,
      final Pageable pageable);

}
