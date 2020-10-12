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
public class TransferenciaDTO extends TransacaoDTO {

  @NotNull Long idContaDestino;

  public TransferenciaDTO(
      Date data,
      BigDecimal valor,
      String descricao,
      Long idResponsavel,
      Long idConta,
      Long idContaDestino) {
    super(data, valor, descricao, idResponsavel, idConta, TipoTransacaoEnum.TRANSFERENCIA);
    this.idContaDestino = idContaDestino;
  }
}
