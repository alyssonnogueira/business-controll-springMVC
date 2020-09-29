package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Conta;
import java.util.List;

public interface IContaConsultaService {

  List<Conta> obterTodasAsContas();

  Conta obterConta(Long idConta);

}
