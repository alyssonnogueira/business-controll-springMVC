package com.dbserver.treinamentospring.dominio.eventos;

import com.dbserver.treinamentospring.dominio.Conta;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class DebitarSaldoEvent {

  private final Conta conta;
  BigDecimal valor;

  public DebitarSaldoEvent(Conta conta, BigDecimal valor) {
    this.conta = conta;
    this.valor = valor;
  }
}
