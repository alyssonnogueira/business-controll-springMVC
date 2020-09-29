package com.dbserver.treinamentospring.dominio;

import com.dbserver.treinamentospring.dominio.enumeradores.TipoContaEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

@Entity()
@Table(name = "CONTA")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@NoArgsConstructor
public class Conta {

  @Setter
  @Id
  @Column(name = "ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  @Column(name = "NOME", nullable = false)
  private String nome;

  @Column(name = "SALDO_INICIAL", nullable = false)
  private BigDecimal saldoInicial;

  @Column(name = "SALDO", nullable = false)
  private BigDecimal saldo;

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO_CONTA", nullable = false)
  private TipoContaEnum tipoConta;

  @CreatedDate
  @Column(name = "CREATED_AT", nullable = false, updatable = false)
  private LocalDateTime dataCriacao;

  @UpdateTimestamp
  @Column(name = "UPDATED_AT", nullable = false)
  private LocalDateTime dataAtualizacao;

  @UpdateTimestamp
  @Column(name = "DELETED_AT")
  private LocalDateTime dataExclusao;

  @ManyToOne()
  @JoinColumn(name = "ID_RESPONSAVEL", nullable = false)
  private Responsavel responsavel;

  public Conta(String nome, BigDecimal saldoInicial, BigDecimal saldo,
      TipoContaEnum tipoConta, Responsavel responsavel) {
    this.nome = nome;
    this.saldoInicial = saldoInicial;
    this.saldo = saldo;
    this.tipoConta = tipoConta;
    this.dataCriacao = LocalDateTime.now();
    this.dataAtualizacao = LocalDateTime.now();
    this.responsavel = responsavel;
  }

  public void setNome(String nome) {
    this.nome = nome;
    this.dataAtualizacao = LocalDateTime.now();
  }

  public void excluirConta() {
    this.dataExclusao = LocalDateTime.now();
  }

  public void debitarSaldo(BigDecimal valor) {
    this.saldo = this.saldo.add(valor);
  }

  public void creditarSaldo(BigDecimal valor) {
    this.saldo = this.saldo.subtract(valor);
  }
}
