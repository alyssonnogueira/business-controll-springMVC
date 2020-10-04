package com.dbserver.treinamentospring.infra;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.ResponsavelRepository.ResponsavelJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ResponsavelRepositoryTest {

  @InjectMocks
  ResponsavelRepository responsavelRepository;

  @Mock
  ResponsavelJpaRepository responsavelJpaRepository;

  @Test
  void deveSalvarUmResponsavel(){
    Responsavel responsavel = ResponsavelDataProvider.criarResponsavel();

    responsavelRepository.salvarResponsavel(responsavel);

    verify(responsavelJpaRepository, times(1)).save(responsavel);
  }

  @Test
  void deveExcluirUmResponsavel() {
    responsavelRepository.excluirResponsavel(1L);

    verify(responsavelJpaRepository, times(1)).deleteById(1L);
  }

}