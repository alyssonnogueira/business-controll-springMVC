package com.dbserver.treinamentospring.web;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

class TransacaoConsultaControllerIT extends BaseControllerIT {

  private static final String INSERIR_RESPONSAVEIS = "../sql/inserirResponsaveis.sql";
  private static final String REMOVER_RESPONSAVEIS = "../sql/removerResponsaveis.sql";
  private static final String INSERIR_CONTAS = "../sql/inserirContas.sql";
  private static final String REMOVER_CONTAS = "../sql/removerContas.sql";
  private static final String INSERIR_TRANSACOES = "../sql/inserirTransacoes.sql";
  private static final String REMOVER_TRANSACOES = "../sql/removerTransacoes.sql";

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS, INSERIR_TRANSACOES },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveListarTodasAsTransacoes() throws Exception {

    mvc.perform(get("/v1/transacoes/")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[*]", hasSize(3)))
        .andExpect(jsonPath("$.[*].id", containsInAnyOrder(1, 2, 3)))
        .andExpect(jsonPath("$.[*].data", containsInAnyOrder("2020-11-09T10:15:00", "2020-11-09T10:15:00", "2020-11-09T10:15:00")))
        .andExpect(jsonPath("$.[*].valor", containsInAnyOrder(100.0, 100.0, 100.0)))
        .andExpect(jsonPath("$.[*].descricao", containsInAnyOrder("Comprei algo", "Salario", "Poupanca")))
        .andExpect(jsonPath("$.[*].responsavel.nome", containsInAnyOrder("Alysson", "Alysson", "Alysson")))
        .andExpect(jsonPath("$.[*].conta.nome", containsInAnyOrder("Conta Digital", "Conta Digital", "Conta Digital")))
        .andExpect(jsonPath("$.[*].tipoTransacao", containsInAnyOrder(TipoTransacaoEnum.DESPESA.name(), TipoTransacaoEnum.RECEITA.name(), TipoTransacaoEnum.TRANSFERENCIA.name())))
        .andExpect(jsonPath("$.[*].dataCriacao", containsInAnyOrder("2020-11-09T10:30:00", "2020-11-09T10:30:00", "2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.[*].dataAtualizacao", containsInAnyOrder("2020-11-09T10:30:00", "2020-11-09T10:30:00", "2020-11-09T10:30:00")));
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS, INSERIR_TRANSACOES },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveBuscarUmaTransacaoComTipoDebito() throws Exception {

    mvc.perform(get("/v1/transacoes/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andExpect(jsonPath("$.data", Matchers.is("2020-11-09T10:15:00")))
        .andExpect(jsonPath("$.valor", Matchers.is(100.0)))
        .andExpect(jsonPath("$.descricao", Matchers.is("Comprei algo")))
        .andExpect(jsonPath("$.responsavel.nome", Matchers.is("Alysson")))
        .andExpect(jsonPath("$.conta.nome", Matchers.is("Conta Digital")))
        .andExpect(jsonPath("$.tipoTransacao", Matchers.is(TipoTransacaoEnum.DESPESA.name())))
        .andExpect(jsonPath("$.dataCriacao", Matchers.is("2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.dataAtualizacao", Matchers.is("2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.categoriaDespesa", Matchers.is(CategoriaDespesaEnum.ALIMENTACAO.name())));
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS, INSERIR_TRANSACOES },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveBuscarUmaTransacaoComTipoReceita() throws Exception {

    mvc.perform(get("/v1/transacoes/2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(2)))
        .andExpect(jsonPath("$.data", Matchers.is("2020-11-09T10:15:00")))
        .andExpect(jsonPath("$.valor", Matchers.is(100.0)))
        .andExpect(jsonPath("$.descricao", Matchers.is("Salario")))
        .andExpect(jsonPath("$.responsavel.nome", Matchers.is("Alysson")))
        .andExpect(jsonPath("$.conta.nome", Matchers.is("Conta Digital")))
        .andExpect(jsonPath("$.tipoTransacao", Matchers.is(TipoTransacaoEnum.RECEITA.name())))
        .andExpect(jsonPath("$.dataCriacao", Matchers.is("2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.dataAtualizacao", Matchers.is("2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.categoriaReceita", Matchers.is(CategoriaReceitaEnum.SALARIO.name())));
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS, INSERIR_TRANSACOES },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveBuscarUmaTransacaoComTipoTransferencia() throws Exception {

    mvc.perform(get("/v1/transacoes/3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(3)))
        .andExpect(jsonPath("$.data", Matchers.is("2020-11-09T10:15:00")))
        .andExpect(jsonPath("$.valor", Matchers.is(100.0)))
        .andExpect(jsonPath("$.descricao", Matchers.is("Poupanca")))
        .andExpect(jsonPath("$.responsavel.nome", Matchers.is("Alysson")))
        .andExpect(jsonPath("$.conta.nome", Matchers.is("Conta Digital")))
        .andExpect(jsonPath("$.tipoTransacao", Matchers.is(TipoTransacaoEnum.TRANSFERENCIA.name())))
        .andExpect(jsonPath("$.dataCriacao", Matchers.is("2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.dataAtualizacao", Matchers.is("2020-11-09T10:30:00")))
        .andExpect(jsonPath("$.contaDestino.nome", Matchers.is("Conta Corrente")));
  }
}