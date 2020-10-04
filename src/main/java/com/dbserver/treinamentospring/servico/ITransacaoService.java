package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.DespesaDTO;
import com.dbserver.treinamentospring.crosscutting.ReceitaDTO;
import com.dbserver.treinamentospring.crosscutting.TransferenciaDTO;

public interface ITransacaoService {

  void criarTransferencia(DespesaDTO despesaDTO);

  void criarTransferencia(ReceitaDTO receitaDTO);

  void criarTransferencia(TransferenciaDTO transferenciaDTO);

  void excluirTransacao(Long transacaoId);

}
