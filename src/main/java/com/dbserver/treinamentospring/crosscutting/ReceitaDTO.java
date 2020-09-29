package com.dbserver.treinamentospring.crosscutting;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceitaDTO extends TransacaoDTO {

  @NotNull
  CategoriaReceitaEnum categoriaReceita;

}
