package com.dbserver.treinamentospring.dominio;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.dominio.eventos.CreditarSaldoEvent;
import com.dbserver.treinamentospring.dominio.eventos.DebitarSaldoEvent;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.DomainEvents;

@Entity
@DiscriminatorValue("RECEITA")
@Getter
@NoArgsConstructor
public class Receita extends Transacao {

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "CATEGORIA_RECEITA", nullable = false)
  private CategoriaReceitaEnum categoriaReceita;

  public Receita(
      LocalDateTime data,
      BigDecimal valor,
      String descricao,
      Responsavel responsavel,
      Conta conta,
      CategoriaReceitaEnum categoriaReceita) {
    super(data, valor, descricao, responsavel, conta, TipoTransacaoEnum.RECEITA);
    this.categoriaReceita = categoriaReceita;
    this.creditarSaldo(this.getConta());
  }

  @Override
  public void excluirTransacao() {
    this.debitarSaldo(this.getConta());
    super.excluirTransacao();
  }
}
