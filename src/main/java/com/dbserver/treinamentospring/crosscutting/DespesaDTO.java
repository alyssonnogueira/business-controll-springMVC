package com.dbserver.treinamentospring.crosscutting;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesaDTO extends TransacaoDTO {

  @NotNull CategoriaDespesaEnum categoriaDespesa;

  public DespesaDTO(
      Date data,
      BigDecimal valor,
      String descricao,
      Long idResponsavel,
      Long idConta,
      CategoriaDespesaEnum categoriaDespesa) {
    super(data, valor, descricao, idResponsavel, idConta, TipoTransacaoEnum.DESPESA);
    this.categoriaDespesa = categoriaDespesa;
  }
}
