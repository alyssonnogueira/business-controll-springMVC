package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Responsavel;
import java.util.List;

public interface IResponsavelConsultaService {

  List<Responsavel> obterTodosOsResponsaveis();

  Responsavel obterResponsavel(Long idResponsavel);

}
