package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.ContaAtualizadaDTO;
import com.dbserver.treinamentospring.crosscutting.ContaDTO;

public interface IContaService {

  void criarConta(ContaDTO contaDTO);

  void atualizarConta(ContaAtualizadaDTO contaAtualizadaDTO);

  void excluirConta(Long contaId);

}
