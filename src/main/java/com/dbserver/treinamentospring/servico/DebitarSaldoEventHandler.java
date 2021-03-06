package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.eventos.DebitarSaldoEvent;
import com.dbserver.treinamentospring.infra.IContaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DebitarSaldoEventHandler {

  private final IContaRepository contaRepository;

  public DebitarSaldoEventHandler(
      IContaRepository contaRepository) {
    this.contaRepository = contaRepository;
  }

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void processar(DebitarSaldoEvent event) {
    Conta conta = event.getConta();
    conta.debitarSaldo(event.getValor());
    contaRepository.save(conta);
  }
}
