package com.dbserver.treinamentospring.dominio;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

@Entity()
@Table(name = "RESPONSAVEL")
@Getter
@NoArgsConstructor
@Where(clause = "DATA_EXCLUSAO IS NULL")
@SQLDelete(sql = "UPDATE RESPONSAVEL SET DATA_EXCLUSAO=CURRENT_TIMESTAMP WHERE ID=?")
public class Responsavel {

  @Setter
  @Id
  @Column(name = "ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  @Column(name = "NOME", nullable = false)
  private String nome;

  @CreatedDate
  @Column(name = "DATA_CRIACAO", nullable = false, updatable = false)
  private LocalDateTime dataCriacao;

  @UpdateTimestamp
  @Column(name = "DATA_ATUALIZACAO", nullable = false)
  private LocalDateTime dataAtualizacao;

  @Column(name = "DATA_EXCLUSAO", nullable = true)
  private LocalDateTime dataExclusao;

  public Responsavel(String nome) {
    this.nome = nome;
    this.dataCriacao = LocalDateTime.now();
    this.dataAtualizacao = LocalDateTime.now();
  }

  public void setNome(String nome) {
    this.nome = nome;
    this.dataAtualizacao = LocalDateTime.now();
  }
}