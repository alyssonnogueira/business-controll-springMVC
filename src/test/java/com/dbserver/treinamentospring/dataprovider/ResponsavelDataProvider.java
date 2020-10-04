package com.dbserver.treinamentospring.dataprovider;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dominio.Responsavel;
import org.mockito.Spy;

public class ResponsavelDataProvider {

  public static Responsavel criarResponsavel() {
    String nome = "João";
    Responsavel responsavel = new Responsavel(nome);
    responsavel.setId(1L);
    return responsavel;
  }

  public static Responsavel criarResponsavel(String nome) {
    Responsavel responsavel = new Responsavel(nome);
    responsavel.setId(1L);
    return responsavel;
  }
}
