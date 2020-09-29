package com.dbserver.treinamentospring.crosscutting;

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
abstract class TransacaoDTO {

  @NotNull
  LocalDateTime data;

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
