package com.dbserver.treinamentospring.crosscutting;

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
abstract class TransacaoDTO {

  @NotNull
  Date data;

  @NotNull
  BigDecimal valor;

  @NotNull
  String descricao;

  @NotNull
  Long idResponsavel;

  @NotNull
  Long idConta;

  @NotNull
  TipoTransacaoEnum tipoTransacao;

}
