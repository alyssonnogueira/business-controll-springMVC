package com.dbserver.treinamentospring.dataprovider;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import java.math.BigDecimal;

public class ContaDataProvider {

  public static Conta criarConta(){
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = new Conta("Banco Digital", BigDecimal.TEN, TipoContaEnum.DEBITO, responsavel);
    conta.setId(1L);
    return conta;
  }

  public static Conta criarConta(String nome){
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = new Conta(nome, BigDecimal.TEN, TipoContaEnum.DEBITO, responsavel);
    conta.setId(1L);
    return conta;
  }

  public static Conta criarConta(Responsavel responsavel){
    Conta conta = new Conta("Banco Digital", BigDecimal.TEN, TipoContaEnum.DEBITO, responsavel);
    conta.setId(1L);
    return conta;
  }

  public static Conta criarContaDebito(){
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = new Conta("Banco Digital", BigDecimal.TEN, TipoContaEnum.DEBITO, responsavel);
    conta.setId(1L);
    return conta;
  }

  public static Conta criarContaCredito(){
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    Conta conta = new Conta("Banco Digital", BigDecimal.TEN, TipoContaEnum.CREDITO, responsavel);
    conta.setId(1L);
    return conta;
  }

}
