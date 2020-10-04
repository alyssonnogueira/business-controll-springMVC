package com.dbserver.treinamentospring.servico;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.crosscutting.ContaAtualizadaDTO;
import com.dbserver.treinamentospring.crosscutting.ContaDTO;
import com.dbserver.treinamentospring.dataprovider.ContaDataProvider;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import com.dbserver.treinamentospring.infra.IContaRepository;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ContaServiceTest {

  @InjectMocks
  ContaService contaService;

  @Mock
  IContaRepository contaRepository;

  @Mock
  IContaConsultaRepository contaConsultaRepository;

  @Mock
  IResponsavelConsultaRepository responsavelConsultaRepository;

  @Test
  void deveChamarFuncaoSalvarAoCriarConta() {
    ContaDTO contaDTO = new ContaDTO("Nome da Conta", BigDecimal.TEN, TipoContaEnum.DEBITO, 1L);

    contaService.criarConta(contaDTO);

    verify(responsavelConsultaRepository, times(1)).obterResponsavel(1L);
    verify(contaRepository, times(1)).save(any());
  }

  @Test
  void deveChamarFuncaoSalvarAoAtualizarConta() {
    ContaAtualizadaDTO contaAtualizadaDTO = new ContaAtualizadaDTO(1L, "Nome da Conta");

    when(contaConsultaRepository.obterConta(1L)).thenReturn(ContaDataProvider.criarConta());

    contaService.atualizarConta(contaAtualizadaDTO);

    verify(contaConsultaRepository, times(1)).obterConta(1L);
    verify(contaRepository, times(1)).save(any());
  }

  @Test
  void deveChamarFuncaoDeletarAoExcluirConta() {
    contaService.excluirConta(1L);

    verify(contaRepository, times(1)).deleteById(1L);
  }

}