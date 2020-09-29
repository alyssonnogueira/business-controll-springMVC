package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.ResponsavelAtualizadoDTO;
import com.dbserver.treinamentospring.crosscutting.ResponsavelDTO;

public interface IResponsavelService {

  void criarResponsavel(ResponsavelDTO responsavelDTO);

  void atualizarResponsavel(ResponsavelAtualizadoDTO responsavelAtualizadoDTO);

  void excluirResponsavel(Long id);

}
