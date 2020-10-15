package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.infra.ITransacaoConsultaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TransacaoConsultaService implements ITransacaoConsultaService {

  private final ITransacaoConsultaRepository transacaoConsultaRepository;

  public TransacaoConsultaService(ITransacaoConsultaRepository transacaoConsultaRepository) {
    this.transacaoConsultaRepository = transacaoConsultaRepository;
  }

  @Override
  public List<Transacao> obterTodasTransacoes() {
    return this.transacaoConsultaRepository.obterTodasTransacoes();
  }

  @Override
  public Page<Transacao> obterTodasTransacoes(
      final String descricaoCategoria,
      final List<Long> idsResponsaveis,
      final List<Long> idsContas,
      final List<TipoTransacaoEnum> tiposTransacao,
      final LocalDateTime dataInicial,
      final LocalDateTime dataFinal,
      final Pageable pageable) {
    return this.transacaoConsultaRepository.obterTodasTransacoes(
        descricaoCategoria,
        idsResponsaveis,
        idsContas,
        tiposTransacao,
        dataInicial,
        dataFinal,
        pageable);
  }

  @Override
  public Transacao obterTransacao(Long idTransacao) {
    return this.transacaoConsultaRepository.obterTransacao(idTransacao);
  }
}
