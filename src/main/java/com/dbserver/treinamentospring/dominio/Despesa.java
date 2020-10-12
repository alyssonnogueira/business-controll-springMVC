package com.dbserver.treinamentospring.dominio;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("DESPESA")
@Getter
@NoArgsConstructor
public class Despesa extends Transacao {

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "CATEGORIA_DESPESA", nullable = false)
  private CategoriaDespesaEnum categoriaDespesa;

  public Despesa(
      Date data,
      BigDecimal valor,
      String descricao,
      Responsavel responsavel,
      Conta conta,
      CategoriaDespesaEnum categoriaDespesaEnum) {
    super(data, valor, descricao, responsavel, conta, TipoTransacaoEnum.DESPESA);
    this.categoriaDespesa = categoriaDespesaEnum;
    this.debitarSaldo(this.getConta());
  }

  @Override
  public void excluirTransacao() {
    this.creditarSaldo(this.getConta());
    super.excluirTransacao();
  }
}
