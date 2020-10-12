package com.dbserver.treinamentospring.dominio;

import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("TRANSFERENCIA")
@Getter
@NoArgsConstructor
public class Transferencia extends Transacao {

  @NotNull
  @ManyToOne
  @JoinColumn(name = "ID_CONTA_DESTINO", nullable = false)
  private Conta contaDestino;

  public Transferencia(
      Date data,
      BigDecimal valor,
      String descricao,
      Responsavel responsavel,
      Conta conta,
      Conta contaDestino) {
    super(data, valor, descricao, responsavel, conta, TipoTransacaoEnum.TRANSFERENCIA);
    this.contaDestino = contaDestino;
    this.debitarSaldo(this.getConta());
    this.creditarSaldo(this.contaDestino);
  }

  @Override
  public void excluirTransacao() {
    this.creditarSaldo(this.getConta());
    this.debitarSaldo(this.getContaDestino());
    super.excluirTransacao();
  }
}
