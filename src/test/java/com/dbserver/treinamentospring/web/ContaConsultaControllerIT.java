package com.dbserver.treinamentospring.web;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

class ContaConsultaControllerIT extends BaseControllerIT {

  private static final String INSERIR_RESPONSAVEIS = "../sql/inserirResponsaveis.sql";
  private static final String REMOVER_RESPONSAVEIS = "../sql/removerResponsaveis.sql";
  private static final String INSERIR_CONTAS = "../sql/inserirContas.sql";
  private static final String REMOVER_CONTAS = "../sql/removerContas.sql";

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveListarTodasAsContas() throws Exception {

    mvc.perform(get("/v1/contas/")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[*]", hasSize(1)))
        .andExpect(jsonPath("$.[*].nome", containsInAnyOrder("Alysson")));
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveBuscarUmaConta() throws Exception {

    mvc.perform(get("/v1/contas/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andExpect(jsonPath("$.nome", Matchers.is("Alysson")))
        .andExpect(jsonPath("$.saldoInicial", Matchers.is(100.0)))
        .andExpect(jsonPath("$.saldo", Matchers.is(100.0)))
        .andExpect(jsonPath("$.tipoConta", Matchers.is(TipoContaEnum.DEBITO.name())))
        .andExpect(jsonPath("$.dataCriacao", Matchers.is("2020-10-09T01:41:51")))
        .andExpect(jsonPath("$.dataAtualizacao", Matchers.is("2020-10-09T01:41:51")));
  }

}