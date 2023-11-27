package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.model.Editor;
import com.lucasengcomp.junit.blog.model.Earnings;
import com.lucasengcomp.junit.blog.model.Post;

import java.math.BigDecimal;
import java.util.Objects;

public class EarningsCalculator {

    private final TextProcessor textProcessor;
    private final BigDecimal premiumBonus;

    public EarningsCalculator(TextProcessor textProcessor,
                              BigDecimal premiumBonus) {
        Objects.requireNonNull(textProcessor);
        Objects.requireNonNull(premiumBonus);
        this.textProcessor = textProcessor;
        this.premiumBonus = premiumBonus;
    }

    public Earnings calculate(Post post) {
        Objects.requireNonNull(post);
        Editor autor = post.getAutor();
        Objects.requireNonNull(autor);

        BigDecimal paymentPerWord  = autor.getPricePerWord();
        int quantityWords = textProcessor.quantityWords(post.getContent());
        BigDecimal totalEarning = paymentPerWord .multiply(BigDecimal.valueOf(quantityWords));

        if (post.getAutor().isPremium()) {
            totalEarning = totalEarning.add(premiumBonus);
        }

        return new Earnings(paymentPerWord , quantityWords, totalEarning);
    }
}
