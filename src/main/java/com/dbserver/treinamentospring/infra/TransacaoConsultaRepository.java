package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Transacao;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransacaoConsultaRepository implements ITransacaoConsultaRepository {

  private final TransacaoJpaRepository transacaoJpaRepository;

  public TransacaoConsultaRepository(
      TransacaoJpaRepository transacaoJpaRepository) {
    this.transacaoJpaRepository = transacaoJpaRepository;
  }

  @Override
  public Transacao obterTransacao(Long idTransacao) {
    return this.transacaoJpaRepository.findById(idTransacao).orElseThrow(
        () -> {
          throw new EntityNotFoundException("Transação não encontrada");
        }
    );
  }

  @Override
  public List<Transacao> obterTodasTransacoes() {
    return this.transacaoJpaRepository.findAll();
  }

  interface TransacaoJpaRepository extends JpaRepository<Transacao, Long> {}
}
