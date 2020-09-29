package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Conta;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContaConsultaRepository implements IContaConsultaRepository {

  private final ContaJpaRepository contaJpaRepository;

  @Autowired
  public ContaConsultaRepository(ContaJpaRepository contaJpaRepository) {
    this.contaJpaRepository = contaJpaRepository;
  }

  @Override
  public Conta obterConta(Long idConta) {
    return this.contaJpaRepository
        .findById(idConta)
        .orElseThrow(
            () -> {
              throw new EntityNotFoundException("Conta não encontrada");
            });
  }

  @Override
  public List<Conta> obterTodasAsContas() {
    return this.contaJpaRepository.findAll();
  }

  interface ContaJpaRepository extends JpaRepository<Conta, Long> {}
}
