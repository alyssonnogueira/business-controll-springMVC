package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Transacao;
import java.util.List;

public interface ITransacaoConsultaService {

  List<Transacao> obterTodasTransacoes();

  Transacao obterTransacao(Long idTransacao);

}
