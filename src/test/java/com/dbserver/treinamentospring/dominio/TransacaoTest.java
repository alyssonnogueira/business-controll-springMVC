package com.dbserver.treinamentospring.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.dbserver.treinamentospring.dataprovider.ContaDataProvider;
import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class TransacaoTest {
  private final Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
  private final Conta conta = ContaDataProvider.criarConta();
  private final LocalDateTime dataTransacao = LocalDateTime.now();
  private final BigDecimal valorTransacao = BigDecimal.valueOf(100);

  @Test
  void deveCriarTransacao() {
    String descricaoDespesa = "Comida";
    CategoriaDespesaEnum categoriaDespesa = CategoriaDespesaEnum.ALIMENTACAO;

    Transacao transacao =
        new Despesa(
            dataTransacao, valorTransacao, descricaoDespesa, responsavel, conta, categoriaDespesa);

    assertEquals(conta, transacao.getConta());
    assertEquals(responsavel, transacao.getResponsavel());
    assertEquals(dataTransacao, transacao.getData());
    assertNotNull(transacao.getDataCriacao());
    assertNotNull(transacao.getDataAtualizacao());
    assertNull(transacao.getDataExclusao());
    assertEquals(descricaoDespesa, transacao.getDescricao());
    assertEquals(valorTransacao, transacao.getValor());
  }

  @Test
  void deveCriarTransacaoDoTipoDespesa() {
    String descricaoDespesa = "Comida";
    CategoriaDespesaEnum categoriaDespesa = CategoriaDespesaEnum.ALIMENTACAO;

    Despesa despesa =
        new Despesa(
            dataTransacao, valorTransacao, descricaoDespesa, responsavel, conta, categoriaDespesa);

    assertEquals(TipoTransacaoEnum.DESPESA, despesa.getTipoTransacao());
    assertEquals(CategoriaDespesaEnum.ALIMENTACAO, despesa.getCategoriaDespesaEnum());
  }

  @Test
  void deveCriarTransacaoDoTipoReceita() {
    String descricaoReceita = "Salário";
    CategoriaReceitaEnum categoriaReceita = CategoriaReceitaEnum.SALARIO;

    Receita receita =
        new Receita(
            dataTransacao, valorTransacao, descricaoReceita, responsavel, conta, categoriaReceita);

    assertEquals(TipoTransacaoEnum.RECEITA, receita.getTipoTransacao());
    assertEquals(CategoriaReceitaEnum.SALARIO, receita.getCategoriaReceita());
  }

  @Test
  void deveCriarTransacaoDoTipoTransferencia() {
    String descricaoTransferencia = "Poupanca";
    Conta contaDestino = ContaDataProvider.criarConta("Porquinho");

    Transferencia transferencia =
        new Transferencia(
            dataTransacao, valorTransacao, descricaoTransferencia, responsavel, conta, contaDestino);

    assertEquals(TipoTransacaoEnum.TRANSFERENCIA, transferencia.getTipoTransacao());
    assertNotEquals(contaDestino.getNome(), transferencia.getConta().getNome());
    assertNotEquals(conta.getNome(), transferencia.getContaDestino().getNome());
    assertEquals(contaDestino.getNome(), transferencia.getContaDestino().getNome());
  }
}
