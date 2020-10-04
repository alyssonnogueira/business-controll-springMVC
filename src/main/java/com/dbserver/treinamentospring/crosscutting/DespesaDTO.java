package com.dbserver.treinamentospring.crosscutting;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DespesaDTO extends TransacaoDTO {

  @NotNull
  CategoriaDespesaEnum categoriaDespesa;

  public DespesaDTO(LocalDateTime data, BigDecimal valor,
      String descricao, Long idResponsavel, Long idConta,
      TipoTransacaoEnum tipoTransacao,
      CategoriaDespesaEnum categoriaDespesa) {
    super(data, valor, descricao, idResponsavel, idConta, tipoTransacao);
    this.categoriaDespesa = categoriaDespesa;
  }
}
