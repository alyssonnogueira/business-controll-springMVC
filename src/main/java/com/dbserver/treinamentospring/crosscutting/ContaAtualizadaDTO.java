package com.dbserver.treinamentospring.crosscutting;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaAtualizadaDTO {

  @NotNull
  Long id;

  @NotNull
  String nome;

}