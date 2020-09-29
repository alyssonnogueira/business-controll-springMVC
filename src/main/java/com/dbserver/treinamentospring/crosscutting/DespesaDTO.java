package com.dbserver.treinamentospring.crosscutting;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.sun.istack.NotNull;
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

}
