package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Transacao;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransacaoConsultaRepository {

  Transacao obterTransacao(Long idTransacao);

  List<Transacao> obterTodasTransacoes();

}
