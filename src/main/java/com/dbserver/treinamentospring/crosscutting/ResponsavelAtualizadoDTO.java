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
public class ResponsavelAtualizadoDTO extends ResponsavelDTO {

  @NotNull
  Long id;

  public ResponsavelAtualizadoDTO(Long id, String nome) {
    super(nome);
    this.id = id;
  }
}
