package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.DespesaDTO;
import com.dbserver.treinamentospring.crosscutting.ReceitaDTO;
import com.dbserver.treinamentospring.crosscutting.TransferenciaDTO;

public interface ITransacaoService {

  void criarTransacao(DespesaDTO despesaDTO);

  void criarTransacao(ReceitaDTO receitaDTO);

  void criarTransacao(TransferenciaDTO transferenciaDTO);

  void excluirTransacao(Long transacaoId);

}
