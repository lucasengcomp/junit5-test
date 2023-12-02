package com.lucasengcomp.junit.ecommerce;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemCartPurchase {

    private final Product product;
    private int quantity;

    public ItemCartPurchase(Product product, int quantity) {
        Objects.requireNonNull(product);
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getValorTotal() {
        return this.product.getValue().multiply(new BigDecimal(quantity));
    }

    public void addQuantity(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException();
        }
        this.quantity += quantidade;
    }

    public void substractQuantity(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException();
        }
        if (quantidade >= this.quantity) {
            throw new IllegalArgumentException();
        }
        this.quantity -= quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCartPurchase that = (ItemCartPurchase) o;
        return quantity == that.quantity && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}