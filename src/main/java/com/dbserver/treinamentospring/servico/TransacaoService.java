package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.DespesaDTO;
import com.dbserver.treinamentospring.crosscutting.ReceitaDTO;
import com.dbserver.treinamentospring.crosscutting.TransferenciaDTO;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Despesa;
import com.dbserver.treinamentospring.dominio.Receita;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.Transferencia;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import com.dbserver.treinamentospring.infra.ITransacaoConsultaRepository;
import com.dbserver.treinamentospring.infra.ITransacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService implements ITransacaoService {

  private final ITransacaoRepository transacaoRepository;
  private final ITransacaoConsultaRepository transacaoConsultaRepository;
  private final IResponsavelConsultaRepository responsavelConsultaRepository;
  private final IContaConsultaRepository contaConsultaRepository;

  public TransacaoService(
      ITransacaoRepository transacaoRepository,
      ITransacaoConsultaRepository transacaoConsultaRepository,
      IResponsavelConsultaRepository responsavelConsultaRepository,
      IContaConsultaRepository contaConsultaRepository) {
    this.transacaoRepository = transacaoRepository;
    this.transacaoConsultaRepository = transacaoConsultaRepository;
    this.responsavelConsultaRepository = responsavelConsultaRepository;
    this.contaConsultaRepository = contaConsultaRepository;
  }

  @Override
  public void criarTransferencia(DespesaDTO despesaDTO) {
    Responsavel responsavel =
        this.responsavelConsultaRepository.obterResponsavel(despesaDTO.getIdResponsavel());
    Conta conta = this.contaConsultaRepository.obterConta(despesaDTO.getIdConta());
    Despesa despesa =
        new Despesa(
            despesaDTO.getData(),
            despesaDTO.getValor(),
            despesaDTO.getDescricao(),
            responsavel,
            conta,
            despesaDTO.getCategoriaDespesa());
    this.transacaoRepository.save(despesa);
  }

  @Override
  public void criarTransferencia(ReceitaDTO receitaDTO) {
    Responsavel responsavel =
        this.responsavelConsultaRepository.obterResponsavel(receitaDTO.getIdResponsavel());
    Conta conta = this.contaConsultaRepository.obterConta(receitaDTO.getIdConta());
    Receita receita =
        new Receita(
            receitaDTO.getData(),
            receitaDTO.getValor(),
            receitaDTO.getDescricao(),
            responsavel,
            conta,
            receitaDTO.getCategoriaReceita());
    this.transacaoRepository.save(receita);
  }

  @Override
  public void criarTransferencia(TransferenciaDTO transferenciaDTO) {
    Responsavel responsavel =
        this.responsavelConsultaRepository.obterResponsavel(transferenciaDTO.getIdResponsavel());
    Conta conta = this.contaConsultaRepository.obterConta(transferenciaDTO.getIdConta());
    Conta contaDestino = this.contaConsultaRepository.obterConta(transferenciaDTO.getIdContaDestino());
    Transferencia transferencia =
        new Transferencia(
            transferenciaDTO.getData(),
            transferenciaDTO.getValor(),
            transferenciaDTO.getDescricao(),
            responsavel,
            conta,
            contaDestino);
    this.transacaoRepository.save(transferencia);
  }

  @Override
  public void excluirTransacao(Long transacaoId) {
    Transacao transacao = this.transacaoConsultaRepository.obterTransacao(transacaoId);
    transacao.excluirTransacao();
    this.transacaoRepository.save(transacao);
  }
}
