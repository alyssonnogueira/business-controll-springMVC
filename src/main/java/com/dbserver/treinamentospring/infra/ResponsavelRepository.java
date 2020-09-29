package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ResponsavelRepository implements IResponsavelRepository {

  private final ResponsavelJpaRepository responsavelJpaRepository;

  public ResponsavelRepository(
      ResponsavelJpaRepository responsavelJpaRepository) {
    this.responsavelJpaRepository = responsavelJpaRepository;
  }

  @Override
  public void salvarResponsavel(Responsavel responsavel) {
    this.responsavelJpaRepository.save(responsavel);
  }

  @Override
  public void excluirResponsavel(Long idResponsavel) {
    this.responsavelJpaRepository.deleteById(idResponsavel);
  }

  interface ResponsavelJpaRepository extends JpaRepository<Responsavel, Long> {}
}
