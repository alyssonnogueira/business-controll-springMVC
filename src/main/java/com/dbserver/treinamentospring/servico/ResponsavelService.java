package com.dbserver.treinamentospring.servico;

import com.dbserver.treinamentospring.crosscutting.ResponsavelAtualizadoDTO;
import com.dbserver.treinamentospring.crosscutting.ResponsavelDTO;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import com.dbserver.treinamentospring.infra.IResponsavelRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResponsavelService implements IResponsavelService {

  private final IResponsavelRepository responsavelRepository;
  private final IResponsavelConsultaRepository responsavelConsultaRepository;

  public ResponsavelService(
      IResponsavelRepository responsavelRepository,
      IResponsavelConsultaRepository responsavelConsultaRepository) {
    this.responsavelRepository = responsavelRepository;
    this.responsavelConsultaRepository = responsavelConsultaRepository;
  }

  @Override
  public void criarResponsavel(ResponsavelDTO responsavelDTO) {
    Responsavel responsavel = new Responsavel(responsavelDTO.getNome());
    this.responsavelRepository.salvarResponsavel(responsavel);
  }

  @Override
  public void atualizarResponsavel(ResponsavelAtualizadoDTO responsavelAtualizadoDTO) {
    Responsavel responsavel = this.responsavelConsultaRepository.obterResponsavel(responsavelAtualizadoDTO.getId());
    responsavel.setNome(responsavelAtualizadoDTO.getNome());
    this.responsavelRepository.salvarResponsavel(responsavel);
  }

  @Override
  public void excluirResponsavel(Long responsavelId) {
    this.responsavelRepository.excluirResponsavel(responsavelId);
  }
}
