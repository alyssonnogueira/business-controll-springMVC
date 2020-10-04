package com.dbserver.treinamentospring.servico;

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
import com.dbserver.treinamentospring.infra.ITransacaoConsultaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TransacaoConsultaServiceTest {

  @InjectMocks
  TransacaoConsultaService transacaoConsultaService;

  @Mock
  ITransacaoConsultaRepository transacaoConsultaRepository;

  @Test
  void deveObterTransacao() {
    Despesa despesa = TransacaoDataProvider.criarDespesa();
    when(transacaoConsultaRepository.obterTransacao(anyLong())).thenReturn(despesa);

    Transacao retornoDespesa = this.transacaoConsultaService.obterTransacao(1L);
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
    when(transacaoConsultaRepository.obterTodasTransacoes()).thenReturn(arrayList);

    List<Transacao> listaDeTransacoes = this.transacaoConsultaService.obterTodasTransacoes();
    assertEquals(3, listaDeTransacoes.size());
    assertEquals(despesa, listaDeTransacoes.get(0));
    assertEquals(receita, listaDeTransacoes.get(1));
    assertEquals(transferencia, listaDeTransacoes.get(2));
  }

  @Test
  void AoObterTodasAsTransacoesDeveRetornarArrayVazio() {
    ArrayList arrayList = new ArrayList<Conta>();
    when(transacaoConsultaRepository.obterTodasTransacoes()).thenReturn(arrayList);

    List<Transacao> listaDeTransacoes = this.transacaoConsultaService.obterTodasTransacoes();
    assertEquals(0, listaDeTransacoes.size());
  }

  @Test
  void naoDeveObterTransacaoQuandoNaoExistirNoBanco() {
    when(transacaoConsultaRepository.obterTransacao(1L)).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> this.transacaoConsultaService.obterTransacao(1L));
  }
}