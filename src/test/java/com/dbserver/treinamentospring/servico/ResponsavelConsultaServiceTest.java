package com.dbserver.treinamentospring.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ResponsavelConsultaServiceTest {

  @InjectMocks
  ResponsavelConsultaService responsavelConsultaService;

  @Mock
  IResponsavelConsultaRepository responsavelConsultaRepository;

  @Test
  void deveObterResponsavel() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    when(responsavelConsultaRepository.obterResponsavel(anyLong())).thenReturn(responsavel);

    Responsavel retornoResponsavel = this.responsavelConsultaService.obterResponsavel(1L);
    assertEquals(responsavel, retornoResponsavel);
  }

  @Test
  void deveObterTodosOsResponsaveis() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    ArrayList arrayList = new ArrayList<Conta>();
    arrayList.add(responsavel);
    when(responsavelConsultaRepository.obterTodosOsResponsaveis()).thenReturn(arrayList);

    List<Responsavel> listaDeResponsaveis = this.responsavelConsultaService.obterTodosOsResponsaveis();
    assertEquals(1, listaDeResponsaveis.size());
    assertEquals(responsavel, listaDeResponsaveis.get(0));
  }

  @Test
  void AoObterTodosOsResponsaveisDeveRetornarArrayVazio() {
    ArrayList arrayList = new ArrayList<Conta>();
    when(responsavelConsultaRepository.obterTodosOsResponsaveis()).thenReturn(arrayList);

    List<Responsavel> listaDeResponsaveis = this.responsavelConsultaService.obterTodosOsResponsaveis();
    assertEquals(0, listaDeResponsaveis.size());
  }

  @Test
  void naoDeveObterResponsavelQuandoNaoExistirNoBanco() {
    when(responsavelConsultaRepository.obterResponsavel(1L)).thenThrow(EntityNotFoundException.class);

    assertThrows(EntityNotFoundException.class, () -> this.responsavelConsultaService.obterResponsavel(1L));
  }
}