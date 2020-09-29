package com.dbserver.treinamentospring.crosscutting;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenciaDTO extends TransacaoDTO {

  @NotNull
  Long idContaDestino;

}
