package com.dbserver.treinamentospring.crosscutting;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
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
public class ReceitaDTO extends TransacaoDTO {

  @NotNull CategoriaReceitaEnum categoriaReceita;

  public ReceitaDTO(
      Date data,
      BigDecimal valor,
      String descricao,
      Long idResponsavel,
      Long idConta,
      CategoriaReceitaEnum categoriaReceita) {
    super(data, valor, descricao, idResponsavel, idConta, TipoTransacaoEnum.RECEITA);
    this.categoriaReceita = categoriaReceita;
  }
}
