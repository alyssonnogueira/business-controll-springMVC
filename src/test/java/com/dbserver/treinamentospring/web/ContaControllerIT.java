package com.dbserver.treinamentospring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dbserver.treinamentospring.crosscutting.ContaAtualizadaDTO;
import com.dbserver.treinamentospring.crosscutting.ContaDTO;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import com.dbserver.treinamentospring.infra.IContaRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

class ContaControllerIT extends BaseControllerIT {

  private static final String INSERIR_RESPONSAVEIS = "../sql/inserirResponsaveis.sql";
  private static final String REMOVER_RESPONSAVEIS = "../sql/removerResponsaveis.sql";
  private static final String INSERIR_CONTAS = "../sql/inserirContas.sql";
  private static final String REMOVER_CONTAS = "../sql/removerContas.sql";

  @Autowired
  private IContaConsultaRepository contaConsultaRepository;

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveCriarConta() throws Exception {
    String nomeBanco = "Banco Digital";
    ContaDTO contaDTO = new ContaDTO(nomeBanco, BigDecimal.TEN, TipoContaEnum.DEBITO,  1L);

    mvc.perform(post("/v1/contas/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(contaDTO)))
        .andExpect(status().isCreated());

    List<Conta> contas = contaConsultaRepository.obterTodasAsContas();

    assertEquals(1, contas.size());
    assertEquals(nomeBanco, contas.get(0).getNome());
    assertTrue(BigDecimal.TEN.compareTo(contas.get(0).getSaldoInicial()) == 0);
    assertTrue(BigDecimal.TEN.compareTo(contas.get(0).getSaldo()) == 0);
    assertEquals(TipoContaEnum.DEBITO, contas.get(0).getTipoConta());
    assertNotNull(contas.get(0).getResponsavel());
    assertEquals(1L, contas.get(0).getResponsavel().getId());
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveAtualizarConta() throws Exception {
    String nomeAtualizadoBanco = "Banco Digital 2";
    ContaAtualizadaDTO contaDTO = new ContaAtualizadaDTO(1L, nomeAtualizadoBanco);

    mvc.perform(put("/v1/contas/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(contaDTO)))
        .andExpect(status().isNoContent());

    Conta conta = contaConsultaRepository.obterConta(1L);

    assertEquals(nomeAtualizadoBanco, conta.getNome());
    assertTrue(BigDecimal.valueOf(100).compareTo(conta.getSaldoInicial()) == 0);
    assertTrue(BigDecimal.valueOf(100).compareTo(conta.getSaldo()) == 0);
    assertEquals(TipoContaEnum.DEBITO, conta.getTipoConta());
    assertNotNull(conta.getResponsavel());
    assertEquals(1L, conta.getResponsavel().getId());
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveExcluirConta() throws Exception {

    mvc.perform(delete("/v1/contas/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    List<Conta> contas = contaConsultaRepository.obterTodasAsContas();

    assertEquals(0, contas.size());
  }
}