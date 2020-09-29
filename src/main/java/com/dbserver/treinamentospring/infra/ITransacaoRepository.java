package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransacaoRepository extends JpaRepository<Transacao, Long> {
}
