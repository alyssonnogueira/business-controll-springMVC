package com.dbserver.treinamentospring.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dataprovider.ContaDataProvider;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.infra.ContaConsultaRepository;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ContaConsultaServiceTest {

  @InjectMocks
  ContaConsultaService contaConsultaService;

  @Mock
  IContaConsultaRepository contaConsultaRepository;

  @Test
  void deveObterConta() {
    Conta conta = ContaDataProvider.criarConta();
    when(contaConsultaRepository.obterConta(anyLong())).thenReturn(conta);

    Conta retornoConta = this.contaConsultaService.obterConta(1L);
    assertEquals(conta, retornoConta);
  }

  @Test
  void deveObterTodasAsContas() {
    Conta conta = ContaDataProvider.criarConta();
    ArrayList arrayList = new ArrayList<Conta>();
    arrayList.add(conta);
    when(contaConsultaRepository.obterTodasAsContas()).thenReturn(arrayList);

    List<Conta> listaDeContas = this.contaConsultaService.obterTodasAsContas();
    assertEquals(1, listaDeContas.size());
    assertEquals(conta, listaDeContas.get(0));
  }

  @Test
  void AoObterTodasAsContasDeveRetornarArrayVazio() {
    ArrayList arrayList = new ArrayList<Conta>();
    when(contaConsultaRepository.obterTodasAsContas()).thenReturn(arrayList);

    List<Conta> listaDeContas = this.contaConsultaService.obterTodasAsContas();
    assertEquals(0, listaDeContas.size());
  }

  @Test
  void naoDeveObterContaQuandoNaoExistirNoBanco() {
    when(contaConsultaRepository.obterConta(1L)).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> this.contaConsultaService.obterConta(1L));
  }

}