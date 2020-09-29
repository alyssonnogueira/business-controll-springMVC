package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Conta;
import java.util.List;

public interface IContaConsultaRepository {

  Conta obterConta(Long idConta);

  List<Conta> obterTodasAsContas();

}
