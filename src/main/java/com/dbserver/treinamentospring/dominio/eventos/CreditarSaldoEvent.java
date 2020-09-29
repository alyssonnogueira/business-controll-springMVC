package com.dbserver.treinamentospring.dominio.eventos;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Transacao;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CreditarSaldoEvent {

  private Conta conta;
  BigDecimal valor;

  public CreditarSaldoEvent(Conta conta, BigDecimal valor) {
    this.conta = conta;
    this.valor = valor;
  }
}
