package com.dbserver.treinamentospring.infra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dataprovider.TransacaoDataProvider;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Despesa;
import com.dbserver.treinamentospring.dominio.Receita;
import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.Transferencia;
import com.dbserver.treinamentospring.infra.TransacaoConsultaRepository.TransacaoJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TransacaoConsultaRepositoryTest {

  @InjectMocks
  TransacaoConsultaRepository transacaoConsultaRepository;

  @Mock
  TransacaoJpaRepository transacaoJpaRepository;

  @Test
  void deveObterTransacao() {
    Despesa despesa = TransacaoDataProvider.criarDespesa();
    when(transacaoJpaRepository.findById(anyLong())).thenReturn(Optional.of(despesa));

    Transacao retornoDespesa = this.transacaoConsultaRepository.obterTransacao(1L);
    assertEquals(despesa, retornoDespesa);
  }

  @Test
  void deveObterTodasAsTransacoes() {
    Despesa despesa = TransacaoDataProvider.criarDespesa();
    Receita receita = TransacaoDataProvider.criarReceita();
    Transferencia transferencia = TransacaoDataProvider.criarTransferencia();
    ArrayList arrayList = new ArrayList<Transacao>();
    arrayList.add(despesa);
    arrayList.add(receita);
    arrayList.add(transferencia);
    when(transacaoJpaRepository.findAll()).thenReturn(arrayList);

    List<Transacao> listaDeTransacoes = this.transacaoConsultaRepository.obterTodasTransacoes();
    assertEquals(3, listaDeTransacoes.size());
    assertEquals(despesa, listaDeTransacoes.get(0));
    assertEquals(receita, listaDeTransacoes.get(1));
    assertEquals(transferencia, listaDeTransacoes.get(2));
  }

  @Test
  void AoObterTodasAsTransacoesDeveRetornarArrayVazio() {
    ArrayList arrayList = new ArrayList<Conta>();
    when(transacaoJpaRepository.findAll()).thenReturn(arrayList);

    List<Transacao> listaDeTransacoes = this.transacaoConsultaRepository.obterTodasTransacoes();
    assertEquals(0, listaDeTransacoes.size());
  }

  @Test
  void naoDeveObterTransacaoQuandoNaoExistirNoBanco() {
    when(transacaoJpaRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> this.transacaoConsultaRepository.obterTransacao(1L));
  }


}