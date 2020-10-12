package com.dbserver.treinamentospring.dataprovider;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Despesa;
import com.dbserver.treinamentospring.dominio.Receita;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.dominio.Transferencia;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class TransacaoDataProvider {

  public static Despesa criarDespesa() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = ContaDataProvider.criarConta();
    Date dataTransacao = new Date();
    BigDecimal valorTransacao = BigDecimal.valueOf(100);
    String descricaoDespesa = "Comida";
    CategoriaDespesaEnum categoriaDespesa = CategoriaDespesaEnum.ALIMENTACAO;

    Despesa despesa = new Despesa(
        dataTransacao, valorTransacao, descricaoDespesa, responsavel, conta, categoriaDespesa);
    despesa.setId(1L);
    return despesa;
  }

  public static Receita criarReceita() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = ContaDataProvider.criarConta();
    Date dataTransacao = new Date();
    BigDecimal valorTransacao = BigDecimal.valueOf(100);
    String descricaoReceita = "Salário";
    CategoriaReceitaEnum categoriaReceita = CategoriaReceitaEnum.SALARIO;

    Receita receita = new Receita(
        dataTransacao, valorTransacao, descricaoReceita, responsavel, conta, categoriaReceita);
    receita.setId(1L);
    return receita;
  }

  public static Transferencia criarTransferencia() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = ContaDataProvider.criarConta();
    Date dataTransacao = new Date();
    BigDecimal valorTransacao = BigDecimal.valueOf(100);
    String descricaoTransferencia = "Poupanca";
    Conta contaDestino = ContaDataProvider.criarConta("Porquinho");

    Transferencia transferencia = new Transferencia(
        dataTransacao, valorTransacao, descricaoTransferencia, responsavel, conta, contaDestino);
    transferencia.setId(1L);
    return transferencia;
  }
}
