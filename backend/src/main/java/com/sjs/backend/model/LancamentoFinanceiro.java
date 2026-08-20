package com.sjs.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class LancamentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Enumerated(EnumType.STRING)
    private CategoriaFinanceira categoria;

    private String descricao;

    private BigDecimal valor;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne
    private Usuario responsavel;

    private String observacoes;

    private LocalDateTime criadoEm = LocalDateTime.now();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public TipoLancamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }
    public CategoriaFinanceira getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaFinanceira categoria) {
        this.categoria = categoria;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public Usuario getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
