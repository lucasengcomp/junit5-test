package com.lucasengcomp.junit.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ShoppingCart {

    private final Client client;
    private final List<ItemCartPurchase> itens;

    public ShoppingCart(Client cliente) {
        this(cliente, new ArrayList<>());
    }

    public ShoppingCart(Client client, List<ItemCartPurchase> itens) {
        Objects.requireNonNull(client);
        Objects.requireNonNull(itens);
        this.client = client;
        this.itens = new ArrayList<>(itens);
    }

    public List<ItemCartPurchase> getItens() {
        return new ArrayList<>(itens);
    }

    public Client getClient() {
        return client;
    }

    public void addProduct(Product product, int quantidade) {
        Objects.requireNonNull(product);
        verifyQuantity(quantidade);

        findItemByProduct(product)
                .ifPresentOrElse(item -> item.addQuantity(quantidade), ()-> addNewItem(product, quantidade));
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product);
        ItemCartPurchase item = findItemByProduct(product).orElseThrow(RuntimeException::new);
        this.itens.remove(item);
    }

    public void incrementQuantityProduct(Product product) {
        Objects.requireNonNull(product);
        ItemCartPurchase item = findItemByProduct(product).orElseThrow(RuntimeException::new);
        item.addQuantity(1);
    }

    public void substractQuantityProduct(Product product) {
        Objects.requireNonNull(product);
        ItemCartPurchase item = findItemByProduct(product).orElseThrow(RuntimeException::new);
        if (item.getQuantity() == 1) {
            itens.remove(item);
        } else {
            item.substractQuantity(1);
        }
    }

    public BigDecimal getTotalValue() {
        return this.itens.stream()
                .map(ItemCartPurchase::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getQuantityProducts() {
        return this.itens.stream()
                .map(ItemCartPurchase::getQuantity)
                .reduce(0, Integer::sum);
    }

    public void clear() {
        this.itens.clear();
    }

    private void verifyQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void addNewItem(Product product, int quantidade) {
        this.itens.add(new ItemCartPurchase(product, quantidade));
    }

    private Optional<ItemCartPurchase> findItemByProduct(Product product) {
        return this.itens
                .stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();
    }
}
