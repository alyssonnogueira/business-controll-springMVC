package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Transacao;
import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public class TransacaoConsultaRepository implements ITransacaoConsultaRepository {

  private final TransacaoJpaRepository transacaoJpaRepository;

  public TransacaoConsultaRepository(TransacaoJpaRepository transacaoJpaRepository) {
    this.transacaoJpaRepository = transacaoJpaRepository;
  }

  @Override
  public Transacao obterTransacao(Long idTransacao) {
    return this.transacaoJpaRepository
        .findById(idTransacao)
        .orElseThrow(
            () -> {
              throw new EntityNotFoundException("Transação não encontrada");
            });
  }

  @Override
  public List<Transacao> obterTodasTransacoes() {
    return this.transacaoJpaRepository.findAll();
  }

  @Override
  public Page<Transacao> obterTodasTransacoes(
      final String descricaoCategoria,
      final List<Long> idsResponsaveis,
      final List<Long> idsContas,
      final List<TipoTransacaoEnum> tiposTransacao,
      final LocalDateTime dataInicial,
      final LocalDateTime dataFinal,
      final Pageable pageable) {
    return this.transacaoJpaRepository.findAllPageAndFilter(
        descricaoCategoria,
        idsResponsaveis,
        idsContas,
        tiposTransacao,
        dataInicial,
        dataFinal,
        pageable);
  }

  interface TransacaoJpaRepository extends JpaRepository<Transacao, Long> {

    @Query(
        "SELECT t FROM Transacao as t "
            + "WHERE "
            + "(UPPER(t.descricao) LIKE UPPER(CONCAT('%', :descricaoCategoria, '%')) OR :descricaoCategoria IS NULL) AND "
            + "(CONCAT(:idsResponsaveis) IS NULL OR t.responsavel.id in :idsResponsaveis) AND "
            + "(CONCAT(:idsContas) IS NULL OR t.conta.id in :idsContas) AND "
            + "(CONCAT(:tiposTransacao) IS NULL OR t.tipoTransacao in :tiposTransacao) AND "
            + "(:dataInicial IS NULL OR t.data >= :dataInicial) AND "
            + "(:dataFinal IS NULL OR t.data <= :dataFinal)"
    )
    Page<Transacao> findAllPageAndFilter(
        @Param("descricaoCategoria") String descricaoCategoria,
        @Param("idsResponsaveis") List<Long> idsResponsaveis,
        @Param("idsContas") List<Long> idsContas,
        @Param("tiposTransacao") List<TipoTransacaoEnum> tiposTransacao,
        @Param("dataInicial") LocalDateTime dataInicial,
        @Param("dataFinal") LocalDateTime dataFinal,
        Pageable pageable);
  }
}
