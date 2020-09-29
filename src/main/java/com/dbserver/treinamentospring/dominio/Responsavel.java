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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity()
@Table(name = "RESPONSAVEL")
@Getter
@NoArgsConstructor
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
  @Column(name = "CREATED_AT", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "UPDATED_AT", nullable = false)
  private LocalDateTime updatedAt;

  public Responsavel(String nome) {
    this.nome = nome;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public void setNome(String nome) {
    this.nome = nome;
    this.updatedAt = LocalDateTime.now();
  }
}