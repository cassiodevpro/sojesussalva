package com.sjs.backend.dto;

import com.sjs.backend.model.CategoriaFinanceira;
import com.sjs.backend.model.FormaPagamento;
import com.sjs.backend.model.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoFinanceiroDTO {
    private TipoLancamento tipo;
    private CategoriaFinanceira categoria;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private FormaPagamento formaPagamento;
    private String observacoes;

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
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
