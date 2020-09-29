package com.dbserver.treinamentospring.dominio;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.dominio.eventos.DebitarSaldoEvent;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.DomainEvents;

@Entity
@DiscriminatorValue("DESPESA")
@Getter
@NoArgsConstructor
public class Despesa extends Transacao {

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "CATEGORIA_DESPESA", nullable = false)
  private CategoriaDespesaEnum categoriaDespesaEnum;

  public Despesa(
      LocalDateTime data,
      BigDecimal valor,
      String descricao,
      Responsavel responsavel,
      Conta conta,
      CategoriaDespesaEnum categoriaDespesaEnum) {
    super(data, valor, descricao, responsavel, conta, TipoTransacaoEnum.DESPESA);
    this.categoriaDespesaEnum = categoriaDespesaEnum;
    this.debitarSaldo(this.getConta());
  }

  @Override
  public void excluirTransacao() {
    this.creditarSaldo(this.getConta());
    super.excluirTransacao();
  }
}
