package com.dbserver.treinamentospring.servico;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.crosscutting.ResponsavelAtualizadoDTO;
import com.dbserver.treinamentospring.crosscutting.ResponsavelDTO;
import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import com.dbserver.treinamentospring.infra.IResponsavelRepository;
import com.dbserver.treinamentospring.infra.ResponsavelConsultaRepository;
import com.dbserver.treinamentospring.infra.ResponsavelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ResponsavelServiceTest {

  @InjectMocks ResponsavelService responsavelService;

  @Mock IResponsavelConsultaRepository responsavelConsultaRepository;

  @Mock IResponsavelRepository responsavelRepository;

  @Test
  void deveVerificarChamarFuncaoSalvarAoCriarResponsavel() {
    ResponsavelDTO responsavelDTO = new ResponsavelDTO("Alysson");
    responsavelService.criarResponsavel(responsavelDTO);

    verify(responsavelRepository, times(1)).salvarResponsavel(any());
  }

  @Test
  void deveVerificarChamarFuncaoSalvarAoAtualizarResponsavel() {
    ResponsavelAtualizadoDTO responsavelDTO = new ResponsavelAtualizadoDTO(1L, "Alysson");
    when(responsavelConsultaRepository.obterResponsavel(1L))
        .thenReturn(ResponsavelDataProvider.criarResponsavel());

    responsavelService.atualizarResponsavel(responsavelDTO);

    verify(responsavelRepository, times(1)).salvarResponsavel(any());
  }

  @Test
  void deveVerificarChamarFuncaoExcluirResponsavel() {
    responsavelService.excluirResponsavel(1L);

    verify(responsavelRepository, times(1)).excluirResponsavel(1L);
  }
}
