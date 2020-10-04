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
public class TransferenciaDTO extends TransacaoDTO {

  @NotNull
  Long idContaDestino;

  public TransferenciaDTO(LocalDateTime data, BigDecimal valor,
      String descricao, Long idResponsavel, Long idConta,
      TipoTransacaoEnum tipoTransacao,
      Long idContaDestino) {
    super(data, valor, descricao, idResponsavel, idConta, tipoTransacao);
    this.idContaDestino = idContaDestino;
  }
}
