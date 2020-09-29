package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.Transferencia;
import com.dbserver.treinamentospring.dominio.eventos.CreditarSaldoEvent;
import com.dbserver.treinamentospring.dominio.eventos.DebitarSaldoEvent;
import com.dbserver.treinamentospring.infra.IContaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CreditarSaldoEventHandler {

  private final IContaRepository contaRepository;

  public CreditarSaldoEventHandler(IContaRepository contaRepository) {
    this.contaRepository = contaRepository;
  }

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void processar(CreditarSaldoEvent event) {
    Conta conta = event.getConta();
    conta.creditarSaldo(event.getValor());
    contaRepository.save(conta);
  }
}
