package com.dbserver.treinamentospring.dominio.enumeradores;

public enum TipoTransacaoEnum {
  DESPESA,
  RECEITA,
  TRANSFERENCIA;

  public boolean isTransferencia() {
    return this == TRANSFERENCIA;
  }
}
