package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.infra.TransacaoConsultaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransacaoConsultaService implements ITransacaoConsultaService {

  private final TransacaoConsultaRepository transacaoConsultaRepository;

  public TransacaoConsultaService(
      TransacaoConsultaRepository transacaoConsultaRepository) {
    this.transacaoConsultaRepository = transacaoConsultaRepository;
  }

  @Override
  public List<Transacao> obterTodasTransacoes() {
    return this.transacaoConsultaRepository.obterTodasTransacoes();
  }

  @Override
  public Transacao obterTransacao(Long idTransacao) {
    return this.transacaoConsultaRepository.obterTransacao(idTransacao);
  }
}
