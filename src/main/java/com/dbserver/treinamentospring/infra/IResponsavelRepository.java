package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Responsavel;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponsavelRepository {

  void salvarResponsavel(Responsavel responsavel);

  void excluirResponsavel(Long idResponsavel);

}
