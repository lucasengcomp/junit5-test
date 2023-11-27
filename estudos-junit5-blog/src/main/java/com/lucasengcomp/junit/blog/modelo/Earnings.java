package com.lucasengcomp.junit.blog.modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class Earnings {

    private BigDecimal valorPagoPorPalavra;
    private int quantidadePalavras;
    private BigDecimal totalGanho;

    public Earnings(BigDecimal valorPagoPorPalavra, int quantidadePalavras, BigDecimal totalGanho) {
        this.valorPagoPorPalavra = valorPagoPorPalavra;
        this.quantidadePalavras = quantidadePalavras;
        this.totalGanho = totalGanho;
    }

    public BigDecimal getValorPagoPorPalavra() {
        return valorPagoPorPalavra;
    }

    public void setValorPagoPorPalavra(BigDecimal valorPagoPorPalavra) {
        this.valorPagoPorPalavra = valorPagoPorPalavra;
    }

    public int getQuantidadePalavras() {
        return quantidadePalavras;
    }

    public void setQuantidadePalavras(int quantidadePalavras) {
        this.quantidadePalavras = quantidadePalavras;
    }

    public BigDecimal getTotalGanho() {
        return totalGanho;
    }

    public void setTotalGanho(BigDecimal totalGanho) {
        this.totalGanho = totalGanho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earnings earnings = (Earnings) o;
        return quantidadePalavras == earnings.quantidadePalavras &&
                Objects.equals(valorPagoPorPalavra, earnings.valorPagoPorPalavra)
                && Objects.equals(totalGanho, earnings.totalGanho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorPagoPorPalavra, quantidadePalavras, totalGanho);
    }
}
