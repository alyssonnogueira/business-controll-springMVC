package com.dbserver.treinamentospring.infra;

import com.dbserver.treinamentospring.dominio.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContaRepository extends JpaRepository<Conta, Long> {

}
