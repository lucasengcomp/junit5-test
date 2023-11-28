package com.lucasengcomp.junit.blog.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Earnings {

    private BigDecimal valuePerWord;
    private int quantityWord;
    private BigDecimal totalEarning;

    public Earnings(BigDecimal valuePerWord, int quantityWord, BigDecimal totalEarning) {
        this.valuePerWord = valuePerWord;
        this.quantityWord = quantityWord;
        this.totalEarning = totalEarning;
    }

    public BigDecimal getValuePerWord() {
        return valuePerWord;
    }

    public void setValuePerWord(BigDecimal valuePerWord) {
        this.valuePerWord = valuePerWord;
    }

    public int getQuantityWord() {
        return quantityWord;
    }

    public void setQuantityWord(int quantityWord) {
        this.quantityWord = quantityWord;
    }

    public BigDecimal getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(BigDecimal totalEarning) {
        this.totalEarning = totalEarning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earnings earnings = (Earnings) o;
        return quantityWord == earnings.quantityWord
                && Objects.equals(valuePerWord, earnings.valuePerWord)
                && Objects.equals(totalEarning, earnings.totalEarning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valuePerWord, quantityWord, totalEarning);
    }
}
