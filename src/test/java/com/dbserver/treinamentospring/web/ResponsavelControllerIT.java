package com.dbserver.treinamentospring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dbserver.treinamentospring.crosscutting.ResponsavelAtualizadoDTO;
import com.dbserver.treinamentospring.crosscutting.ResponsavelDTO;
import com.dbserver.treinamentospring.dominio.Responsavel;
import com.dbserver.treinamentospring.infra.IResponsavelConsultaRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

class ResponsavelControllerIT extends BaseControllerIT {

  private static final String INSERIR_RESPONSAVEIS = "../sql/inserirResponsaveis.sql";
  private static final String REMOVER_RESPONSAVEIS = "../sql/removerResponsaveis.sql";

  @Autowired
  IResponsavelConsultaRepository responsavelConsultaRepository;

  @Test
  @Sql(value = REMOVER_RESPONSAVEIS, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveCriarUmResponsavel() throws Exception {
      ResponsavelDTO responsavelDTO = new ResponsavelDTO("Alysson");
      mvc.perform(post("/v1/responsaveis/")
          .contentType(MediaType.APPLICATION_JSON)
          .content(toJson(responsavelDTO)))
          .andExpect(status().isCreated());

      List<Responsavel> responsaveis = responsavelConsultaRepository.obterTodosOsResponsaveis();
      assertEquals(1, responsaveis.size());
      assertEquals("Alysson", responsaveis.get(0).getNome());
  }

  @Test
  @Sql(value = INSERIR_RESPONSAVEIS,
       executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = REMOVER_RESPONSAVEIS, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveAtualizarUmResponsavel() throws Exception {
    ResponsavelAtualizadoDTO responsavelDTO = new ResponsavelAtualizadoDTO(1L, "José");
    mvc.perform(put("/v1/responsaveis/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(responsavelDTO)))
        .andExpect(status().isNoContent());

    Responsavel responsaveis = responsavelConsultaRepository.obterResponsavel(1L);
    assertEquals("José", responsaveis.getNome());
  }

  @Test
  @Sql(value = INSERIR_RESPONSAVEIS,
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = REMOVER_RESPONSAVEIS, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void deveExcluirUmResponsavel() throws Exception {

    mvc.perform(delete("/v1/responsaveis/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    List<Responsavel> responsaveis = responsavelConsultaRepository.obterTodosOsResponsaveis();
    assertEquals(0, responsaveis.size());
  }
}