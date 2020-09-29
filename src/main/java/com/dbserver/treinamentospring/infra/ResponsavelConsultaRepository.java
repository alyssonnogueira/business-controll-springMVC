package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Responsavel;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ResponsavelConsultaRepository implements IResponsavelConsultaRepository {

  private final ResponsavelJpaRepository responsavelJpaRepository;

  public ResponsavelConsultaRepository(
      ResponsavelJpaRepository responsavelJpaRepository) {
    this.responsavelJpaRepository = responsavelJpaRepository;
  }

  @Override
  public Responsavel obterResponsavel(Long idResponsavel) {
    return responsavelJpaRepository.findById(idResponsavel).orElseThrow( () -> {
      throw new EntityNotFoundException("Responsavel não encontrado");
    });
  }

  @Override
  public List<Responsavel> obterTodosOsResponsaveis() {
    return this.responsavelJpaRepository.findAll();
  }

  interface ResponsavelJpaRepository extends JpaRepository<Responsavel, Long> {}
}
