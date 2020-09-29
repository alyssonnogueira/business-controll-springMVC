package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Conta;
import com.dbserver.treinamentospring.infra.IContaConsultaRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContaConsultaService implements IContaConsultaService {

  private final IContaConsultaRepository contaConsultaRepository;

  public ContaConsultaService(IContaConsultaRepository contaConsultaRepository) {
    this.contaConsultaRepository = contaConsultaRepository;
  }

  @Override
  public List<Conta> obterTodasAsContas() {
    return this.contaConsultaRepository.obterTodasAsContas();
  }

  @Override
  public Conta obterConta(Long idConta) {
    return this.contaConsultaRepository.obterConta(idConta);
  }
}
