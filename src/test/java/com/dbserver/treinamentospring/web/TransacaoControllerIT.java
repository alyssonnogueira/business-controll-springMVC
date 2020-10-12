package com.dbserver.treinamentospring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dbserver.treinamentospring.crosscutting.DespesaDTO;
import com.dbserver.treinamentospring.crosscutting.ReceitaDTO;
import com.dbserver.treinamentospring.crosscutting.TransferenciaDTO;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Despesa;
import com.dbserver.treinamentospring.dominio.Receita;
import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.Transferencia;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import com.dbserver.treinamentospring.infra.ITransacaoConsultaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

class TransacaoControllerIT extends BaseControllerIT {

  private static final String INSERIR_RESPONSAVEIS = "../sql/inserirResponsaveis.sql";
  private static final String REMOVER_RESPONSAVEIS = "../sql/removerResponsaveis.sql";
  private static final String INSERIR_CONTAS = "../sql/inserirContas.sql";
  private static final String REMOVER_CONTAS = "../sql/removerContas.sql";
  private static final String INSERIR_TRANSACOES = "../sql/inserirTransacoes.sql";
  private static final String REMOVER_TRANSACOES = "../sql/removerTransacoes.sql";

  @Autowired
  private ITransacaoConsultaRepository transacaoConsultaRepository;

  @Autowired
  private IContaConsultaRepository contaConsultaRepository;

  Date data = new Date();
  BigDecimal valor = BigDecimal.valueOf(100);
  Long idResponsavel = 1L;
  Long idConta = 1L;

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveCriarDespesa() throws Exception {
    String descricao = "Supermercado X";
    DespesaDTO despesaDTO = new DespesaDTO(data, valor, descricao, idResponsavel, idConta, CategoriaDespesaEnum.MERCADO);

    mvc.perform(post("/v1/transacoes/despesa")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(despesaDTO)))
        .andExpect(status().isCreated());

    List<Transacao> transacoes = transacaoConsultaRepository.obterTodasTransacoes();

    assertEquals(1, transacoes.size());
    assertEquals(LocalDateTime.ofInstant(data.toInstant(), ZoneId.of("America/Sao_Paulo")), transacoes.get(0).getData());
    assertTrue(valor.compareTo(transacoes.get(0).getValor()) == 0);
    assertEquals(descricao, transacoes.get(0).getDescricao());
    assertEquals(idResponsavel, transacoes.get(0).getResponsavel().getId());
    assertEquals(idConta, transacoes.get(0).getConta().getId());
    assertEquals(TipoTransacaoEnum.DESPESA, transacoes.get(0).getTipoTransacao());
    assertTrue(transacoes.get(0) instanceof Despesa);
    Despesa despesa = (Despesa) transacoes.get(0);
    assertEquals(CategoriaDespesaEnum.MERCADO, despesa.getCategoriaDespesa());
    assertNotEquals(despesa.getConta().getSaldoInicial(), despesa.getConta().getSaldo());
    assertEquals(BigDecimal.ZERO.doubleValue(), despesa.getConta().getSaldo().doubleValue());
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveCriarReceita() throws Exception {
    String descricao = "Salario";
    ReceitaDTO receitaDTO = new ReceitaDTO(data, valor, descricao, idResponsavel, idConta, CategoriaReceitaEnum.SALARIO);

    mvc.perform(post("/v1/transacoes/receita")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(receitaDTO)))
        .andExpect(status().isCreated());

    List<Transacao> transacoes = transacaoConsultaRepository.obterTodasTransacoes();

    assertEquals(1, transacoes.size());
    assertEquals(LocalDateTime.ofInstant(data.toInstant(), ZoneId.of("America/Sao_Paulo")), transacoes.get(0).getData());
    assertEquals(valor.doubleValue(), transacoes.get(0).getValor().doubleValue());
    assertEquals(descricao, transacoes.get(0).getDescricao());
    assertEquals(idResponsavel, transacoes.get(0).getResponsavel().getId());
    assertEquals(idConta, transacoes.get(0).getConta().getId());
    assertEquals(TipoTransacaoEnum.RECEITA, transacoes.get(0).getTipoTransacao());
    assertTrue(transacoes.get(0) instanceof Receita);
    Receita receita = (Receita) transacoes.get(0);
    assertEquals(CategoriaReceitaEnum.SALARIO, receita.getCategoriaReceita());
    assertNotEquals(receita.getConta().getSaldoInicial(), receita.getConta().getSaldo());
    assertEquals(BigDecimal.valueOf(200).doubleValue(), receita.getConta().getSaldo().doubleValue());
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveCriarTransferenciaEntreContas() throws Exception {
    String descricao = "TED";
    Long idConta2 = 2L;
    TransferenciaDTO transferenciaDTO = new TransferenciaDTO(data, valor, descricao, idResponsavel, idConta, idConta2);

    mvc.perform(post("/v1/transacoes/transferencia")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(transferenciaDTO)))
        .andExpect(status().isCreated());

    List<Transacao> transacoes = transacaoConsultaRepository.obterTodasTransacoes();

    assertEquals(1, transacoes.size());
    assertEquals(LocalDateTime.ofInstant(data.toInstant(), ZoneId.of("America/Sao_Paulo")), transacoes.get(0).getData());
    assertEquals(valor.doubleValue(), transacoes.get(0).getValor().doubleValue());
    assertEquals(descricao, transacoes.get(0).getDescricao());
    assertEquals(idResponsavel, transacoes.get(0).getResponsavel().getId());
    assertEquals(idConta, transacoes.get(0).getConta().getId());
    assertEquals(TipoTransacaoEnum.TRANSFERENCIA, transacoes.get(0).getTipoTransacao());
    assertTrue(transacoes.get(0) instanceof Transferencia);
    Transferencia transferencia = (Transferencia) transacoes.get(0);

    assertNotEquals(transferencia.getConta().getSaldoInicial(), transferencia.getConta().getSaldo());
    assertEquals(BigDecimal.valueOf(0).doubleValue(), transferencia.getConta().getSaldo().doubleValue());

    assertNotEquals(transferencia.getContaDestino().getSaldoInicial(), transferencia.getContaDestino().getSaldo());
    assertEquals(BigDecimal.valueOf(200).doubleValue(), transferencia.getContaDestino().getSaldo().doubleValue());
  }

  @Test
  @Sql(value = { INSERIR_RESPONSAVEIS, INSERIR_CONTAS, INSERIR_TRANSACOES },
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = { REMOVER_TRANSACOES, REMOVER_CONTAS, REMOVER_RESPONSAVEIS }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveExcluirTransferencia() throws Exception {

    mvc.perform(delete("/v1/transacoes/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    List<Transacao> transacoes = transacaoConsultaRepository.obterTodasTransacoes();
    assertEquals(2, transacoes.size());

    Conta conta = contaConsultaRepository.obterConta(1L);
    assertNotEquals(conta.getSaldoInicial().doubleValue(), conta.getSaldo().doubleValue());
    assertEquals(BigDecimal.valueOf(200).doubleValue(), conta.getSaldo().doubleValue());
  }
}