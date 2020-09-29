package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Responsavel;
import java.util.List;

public interface IResponsavelConsultaRepository {

  Responsavel obterResponsavel(Long idResponsavel);

  List<Responsavel> obterTodosOsResponsaveis();

}
