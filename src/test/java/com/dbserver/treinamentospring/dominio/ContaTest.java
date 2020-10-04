package com.dbserver.treinamentospring.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ContaTest {

  @Test
  void deveCriarUmaConta() {
    String nomeResponsavel = "Responsavel";
    Responsavel responsavel = Mockito.mock(Responsavel.class);
    when(responsavel.getNome()).thenReturn(nomeResponsavel);

    String nomeConta = "Banco Digital";
    Conta conta = new Conta(nomeConta, BigDecimal.TEN, TipoContaEnum.DEBITO, responsavel);

    assertEquals(nomeConta, conta.getNome());
    assertNotNull(conta.getDataCriacao());
    assertNotNull(conta.getDataAtualizacao());
    assertNull(conta.getDataExclusao());
    assertEquals(nomeResponsavel, conta.getResponsavel().getNome());
    assertEquals(BigDecimal.TEN, conta.getSaldoInicial());
    assertEquals(BigDecimal.TEN, conta.getSaldo());
  }

  @Test
  void deveAtualizarNomeDaConta() {
    String nomeConta = "Fintech";
    String novoNomeDaConta = "Banco Digital";
    Responsavel responsavel = Mockito.mock(Responsavel.class);
    when(responsavel.getNome()).thenReturn(novoNomeDaConta);
    Conta conta = new Conta(nomeConta, BigDecimal.valueOf(0), TipoContaEnum.DEBITO, responsavel);

    LocalDateTime antigaDataAtualizacao = responsavel.getDataAtualizacao();
    conta.setNome(novoNomeDaConta);

    assertEquals(conta.getNome(), novoNomeDaConta);
    assertNotEquals(conta.getDataAtualizacao(), antigaDataAtualizacao);
  }

  @Test
  void deveDebitarSaldoDaConta() {
    String nomeConta = "Banco Digital";
    BigDecimal saldoOriginal = BigDecimal.valueOf(1000);
    Responsavel responsavel = Mockito.mock(Responsavel.class);
    Conta conta = new Conta(nomeConta, saldoOriginal, TipoContaEnum.DEBITO, responsavel);
    BigDecimal valorDespesa = BigDecimal.valueOf(500);

    conta.debitarSaldo(valorDespesa);

    assertEquals(BigDecimal.valueOf(500), conta.getSaldo());
    assertEquals(saldoOriginal, conta.getSaldoInicial());
  }

  @Test
  void deveCreditarSaldoDaConta() {
    String nomeConta = "Banco Digital";
    BigDecimal saldoOriginal = BigDecimal.valueOf(1000);
    Responsavel responsavel = Mockito.mock(Responsavel.class);
    Conta conta = new Conta(nomeConta, saldoOriginal, TipoContaEnum.DEBITO, responsavel);
    BigDecimal valorCredito = BigDecimal.valueOf(500);

    conta.creditarSaldo(valorCredito);

    assertEquals(BigDecimal.valueOf(1500), conta.getSaldo());
    assertEquals(saldoOriginal, conta.getSaldoInicial());
  }
}