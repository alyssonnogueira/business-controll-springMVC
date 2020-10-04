package com.dbserver.treinamentospring.infra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dataprovider.ContaDataProvider;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.infra.ContaConsultaRepository.ContaJpaRepository;
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
class ContaConsultaRepositoryTest {

  @InjectMocks
  ContaConsultaRepository contaConsultaRepository;

  @Mock
  ContaJpaRepository contaJpaRepository;

  @Test
  void deveObterConta() {
    Conta conta = ContaDataProvider.criarConta();
    when(contaJpaRepository.findById(anyLong())).thenReturn(Optional.of(conta));

    Conta retornConta = this.contaConsultaRepository.obterConta(1L);
    assertEquals(conta, retornConta);
  }

  @Test
  void deveObterTodasAsContas() {
    Conta conta = ContaDataProvider.criarConta();
    ArrayList arrayList = new ArrayList<Conta>();
    arrayList.add(conta);
    when(contaJpaRepository.findAll()).thenReturn(arrayList);

    List<Conta> listaDeContas = this.contaConsultaRepository.obterTodasAsContas();
    assertEquals(1, listaDeContas.size());
    assertEquals(conta, listaDeContas.get(0));
  }

  @Test
  void AoObterTodasAsContasDeveRetornarArrayVazio() {
    ArrayList arrayList = new ArrayList<Conta>();
    when(contaJpaRepository.findAll()).thenReturn(arrayList);

    List<Conta> listaDeContas = this.contaConsultaRepository.obterTodasAsContas();
    assertEquals(0, listaDeContas.size());
  }

  @Test
  void naoDeveObterContaQuandoNaoExistirNoBanco() {
    when(contaJpaRepository.findById(2L)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> this.contaConsultaRepository.obterConta(1L));
  }

}