package com.dbserver.treinamentospring.crosscutting;

import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

  @NotNull
  String nome;

  @NotNull
  BigDecimal saldoInicial;

  @NotNull
  TipoContaEnum tipoConta;

  @NotNull
  Long idResponsavel;

}
