package com.dbserver.treinamentospring.servico;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dbserver.treinamentospring.crosscutting.DespesaDTO;
import com.dbserver.treinamentospring.crosscutting.ReceitaDTO;
import com.dbserver.treinamentospring.crosscutting.TransferenciaDTO;
import com.dbserver.treinamentospring.dataprovider.ContaDataProvider;
import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import com.dbserver.treinamentospring.dataprovider.TransacaoDataProvider;
import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaDespesaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.CategoriaReceitaEnum;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import com.dbserver.treinamentospring.infra.ITransacaoConsultaRepository;
import com.dbserver.treinamentospring.infra.ITransacaoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TransacaoServiceTest {

  @InjectMocks TransacaoService transacaoService;

  @Mock ITransacaoConsultaRepository transacaoConsultaRepository;

  @Mock ITransacaoRepository transacaoRepository;

  @Mock IResponsavelConsultaRepository responsavelConsultaRepository;

  @Mock IContaConsultaRepository contaConsultaRepository;

  @Test
  void deveVerificarACriacaoDeUmaDespesa() {
    DespesaDTO despesaDTO =
        new DespesaDTO(
            LocalDateTime.now(),
            BigDecimal.TEN,
            "descricao",
            1L,
            1L,
            TipoTransacaoEnum.DESPESA,
            CategoriaDespesaEnum.ALIMENTACAO);

    when(responsavelConsultaRepository.obterResponsavel(1L))
        .thenReturn(ResponsavelDataProvider.criarResponsavel());

    when(contaConsultaRepository.obterConta(1L)).thenReturn(ContaDataProvider.criarConta());

    transacaoService.criarTransferencia(despesaDTO);

    verify(transacaoRepository, times(1)).save(any());
  }

  @Test
  void deveVerificarACriacaoDeUmaReceita() {
    ReceitaDTO receitaDTO =
        new ReceitaDTO(
            LocalDateTime.now(),
            BigDecimal.TEN,
            "descricao",
            1L,
            1L,
            TipoTransacaoEnum.DESPESA,
            CategoriaReceitaEnum.SALARIO);

    when(responsavelConsultaRepository.obterResponsavel(1L))
        .thenReturn(ResponsavelDataProvider.criarResponsavel());

    when(contaConsultaRepository.obterConta(1L)).thenReturn(ContaDataProvider.criarConta());

    transacaoService.criarTransferencia(receitaDTO);

    verify(transacaoRepository, times(1)).save(any());
  }

  @Test
  void deveVerificarACriacaoDeUmaTransferencia() {
    TransferenciaDTO transferenciaDTO =
        new TransferenciaDTO(
            LocalDateTime.now(),
            BigDecimal.TEN,
            "descricao",
            1L,
            1L,
            TipoTransacaoEnum.DESPESA,
            2L);

    when(responsavelConsultaRepository.obterResponsavel(1L))
        .thenReturn(ResponsavelDataProvider.criarResponsavel());

    when(contaConsultaRepository.obterConta(1L)).thenReturn(ContaDataProvider.criarConta());
    when(contaConsultaRepository.obterConta(2L))
        .thenReturn(ContaDataProvider.criarConta("Conta 2"));

    transacaoService.criarTransferencia(transferenciaDTO);

    verify(transacaoRepository, times(1)).save(any());
  }

  @Test
  void deveVerificarAExclusaoDeUmaTransacao() {
    Transacao transacao = spy(TransacaoDataProvider.criarDespesa());

    when(transacaoConsultaRepository.obterTransacao(1L)).thenReturn(transacao);

    transacaoService.excluirTransacao(1L);

    verify(transacao, times(1)).excluirTransacao();
    verify(transacaoRepository, times(1)).save(any());
  }
}
