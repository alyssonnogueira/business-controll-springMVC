package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.ContaAtualizadaDTO;
import com.dbserver.treinamentospring.crosscutting.ContaDTO;
import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import com.dbserver.treinamentospring.infra.IContaRepository;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContaService implements IContaService {

  private final IContaRepository contaRepository;
  private final IContaConsultaRepository contaConsultaRepository;
  private final IResponsavelConsultaRepository responsavelConsultaRepository;

  public ContaService(
      IContaRepository contaRepository,
      IContaConsultaRepository contaConsultaRepository,
      IResponsavelConsultaRepository responsavelConsultaRepository) {
    this.contaRepository = contaRepository;
    this.contaConsultaRepository = contaConsultaRepository;
    this.responsavelConsultaRepository = responsavelConsultaRepository;
  }

  @Override
  public void criarConta(ContaDTO contaDTO) {
    Responsavel responsavel =
        this.responsavelConsultaRepository.obterResponsavel(contaDTO.getIdResponsavel());
    Conta conta =
        new Conta(
            contaDTO.getNome(),
            contaDTO.getSaldoInicial(),
            contaDTO.getSaldo(),
            contaDTO.getTipoConta(),
            responsavel);
    this.contaRepository.save(conta);
  }

  @Override
  public void atualizarConta(ContaAtualizadaDTO contaAtualizadaDTO) {
    Conta conta = this.contaConsultaRepository.obterConta(contaAtualizadaDTO.getId());
    this.contaRepository.save(conta);
  }

  @Override
  public void excluirConta(Long contaId) {
    this.contaRepository.deleteById(contaId);
  }
}
