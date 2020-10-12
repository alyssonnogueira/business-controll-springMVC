package com.dbserver.treinamentospring.dominio;

import com.dbserver.treinamentospring.dominio.enumeradores.TipoTransacaoEnum;
import com.dbserver.treinamentospring.dominio.eventos.CreditarSaldoEvent;
import com.dbserver.treinamentospring.dominio.eventos.DebitarSaldoEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.DomainEvents;

@Entity()
@Table(name = "TRANSACAO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_TRANSACAO", discriminatorType = DiscriminatorType.STRING)
@Where(clause = "DATA_EXCLUSAO IS NULL")
@SQLDelete(sql = "UPDATE TRANSACAO SET DATA_EXCLUSAO=CURRENT_TIMESTAMP WHERE ID=?")
@Getter
@NoArgsConstructor
public abstract class Transacao {

  @Setter
  @Id
  @Column(name = "ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  @CreatedDate
  @Column(name = "DATA_TRANSACAO", nullable = false)
  private LocalDateTime data;

  @Column(name = "VALOR", nullable = false)
  private BigDecimal valor;

  @Column(name = "DESCRICAO", nullable = false)
  private String descricao;

  @ManyToOne
  @JoinColumn(name = "ID_RESPONSAVEL", nullable = false)
  private Responsavel responsavel;

  @ManyToOne
  @JoinColumn(name = "ID_CONTA", nullable = false)
  private Conta conta;

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO_TRANSACAO", nullable = false, insertable = false, updatable = false)
  private TipoTransacaoEnum tipoTransacao;

  @CreatedDate
  @Column(name = "DATA_CRIACAO", nullable = false, updatable = false)
  private LocalDateTime dataCriacao;

  @UpdateTimestamp
  @Column(name = "DATA_ATUALIZACAO", nullable = false)
  private LocalDateTime dataAtualizacao;

  @Column(name = "DATA_EXCLUSAO")
  private LocalDateTime dataExclusao;

  @Transient
  private final List<Object> events = new ArrayList<>();

  protected Transacao(
      Date data,
      BigDecimal valor,
      String descricao,
      Responsavel responsavel,
      Conta conta,
      TipoTransacaoEnum tipoTransacao) {
    super();
    this.data = LocalDateTime.ofInstant(data.toInstant(), ZoneId.of("America/Sao_Paulo"));
    this.valor = valor;
    this.descricao = descricao;
    this.responsavel = responsavel;
    this.conta = conta;
    this.tipoTransacao = tipoTransacao;
    this.dataCriacao = LocalDateTime.now();
    this.dataAtualizacao = LocalDateTime.now();
  }

  protected void debitarSaldo(Conta conta) {
    this.events.add(new DebitarSaldoEvent(conta, this.getValor()));
  }

  protected void creditarSaldo(Conta conta) {
    this.events.add(new CreditarSaldoEvent(conta, this.getValor()));
  }

  @DomainEvents
  public Collection<Object> eventoAlterarSaldoConta() {
    return events;
  }

  public void excluirTransacao() {
    this.dataExclusao = LocalDateTime.now();
  }
}
