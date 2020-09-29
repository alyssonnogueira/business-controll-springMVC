package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResponsavelConsultaService implements IResponsavelConsultaService {

  private final IResponsavelConsultaRepository responsavelConsultaRepository;

  public ResponsavelConsultaService(IResponsavelConsultaRepository responsavelConsultaRepository) {
    this.responsavelConsultaRepository = responsavelConsultaRepository;
  }

  @Override
  public List<Responsavel> obterTodosOsResponsaveis() {
    return this.responsavelConsultaRepository.obterTodosOsResponsaveis();
  }

  @Override
  public Responsavel obterResponsavel(Long idResponsavel) {
    return this.responsavelConsultaRepository.obterResponsavel(idResponsavel);
  }
}
