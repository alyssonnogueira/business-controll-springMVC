package com.dbserver.treinamentospring.infra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.ResponsavelConsultaRepository.ResponsavelJpaRepository;
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
class ResponsavelConsultaRepositoryTest {

  @InjectMocks
  ResponsavelConsultaRepository responsavelConsultaRepository;

  @Mock
  ResponsavelJpaRepository responsavelJpaRepository;

  @Test
  void deveObterResponsavel() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    when(responsavelJpaRepository.findById(anyLong())).thenReturn(Optional.of(responsavel));

    Responsavel retornoResponsavel = this.responsavelConsultaRepository.obterResponsavel(1L);
    assertEquals(responsavel, retornoResponsavel);
  }

  @Test
  void deveObterTodosOsResponsaveis() {
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();
    ArrayList arrayList = new ArrayList<Responsavel>();
    arrayList.add(responsavel);
    when(responsavelJpaRepository.findAll()).thenReturn(arrayList);

    List<Responsavel> listaDeResponsaveis = this.responsavelConsultaRepository.obterTodosOsResponsaveis();
    assertEquals(1, listaDeResponsaveis.size());
    assertEquals(responsavel, listaDeResponsaveis.get(0));
  }

  @Test
  void AoObterTodosOsResponsaveisDeveRetornarArrayVazio() {
    ArrayList arrayList = new ArrayList<Responsavel>();
    when(responsavelJpaRepository.findAll()).thenReturn(arrayList);

    List<Responsavel> listaDeResponsaveis = this.responsavelConsultaRepository.obterTodosOsResponsaveis();
    assertEquals(0, listaDeResponsaveis.size());
  }

  @Test
  void naoDeveObterResponsavelQuandoNaoExistirNoBanco() {
    when(responsavelJpaRepository.findById(2L)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> this.responsavelConsultaRepository.obterResponsavel(1L));
  }

}