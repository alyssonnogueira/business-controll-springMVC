package com.dbserver.treinamentospring.dominio;

import static org.junit.jupiter.api.Assertions.*;

import com.dbserver.treinamentospring.dataprovider.ResponsavelDataProvider;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ResponsavelTest {

  @Test
  void deveCriarUmResponsavel() {
    String nome = "João";
    Responsavel responsavel = new Responsavel(nome);

    assertEquals(responsavel.getNome(), nome);
    assertNotNull(responsavel.getDataCriacao());
    assertNotNull(responsavel.getDataAtualizacao());
    assertNull(responsavel.getDataExclusao());
  }

  @Test
  void deveAtualizarNomeDoResponsavel() {
    String nome = "João";
    String novoNome = "José";
    Responsavel responsavel = new Responsavel(nome);
    LocalDateTime antigaDataAtualizacao = responsavel.getDataAtualizacao();
    responsavel.setNome(novoNome);

    assertEquals(responsavel.getNome(), novoNome);
    assertNotEquals(responsavel.getDataAtualizacao(), antigaDataAtualizacao);
  }
}