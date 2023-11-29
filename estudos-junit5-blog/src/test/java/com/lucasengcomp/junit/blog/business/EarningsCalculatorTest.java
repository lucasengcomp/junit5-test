package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.CustomDisplayNameGenerator;
import com.lucasengcomp.junit.blog.model.Earnings;
import com.lucasengcomp.junit.blog.model.Editor;
import com.lucasengcomp.junit.blog.model.Post;
import com.lucasengcomp.junit.blog.utility.TextProcessorSimple;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class EarningsCalculatorTest {

    static EarningsCalculator calculator;
    Editor author;
    Post post;

    @BeforeAll
    static void beforeAll() {
        calculator = new EarningsCalculator(new TextProcessorSimple(), BigDecimal.TEN);
    }

    @BeforeEach
    void beforeEach() {
       author = new Editor(1L, "Lucas Galvao", "lucas@gmail.com", new BigDecimal(5), true);
       post = new Post(1L, "Test Junit5", "The best tests with Junit5", author,
                "Ecosistem tests Junit5", null, false, false);
    }

    @Test
    void givenPostMustCalculateTotalEarnings() {
        Earnings earnings = calculator.calculate(post);
        assertEquals(new BigDecimal("35"), earnings.getTotalEarning());
    }

    @Test
    void givenPostMustCalculateNumberWords() {
        Earnings earnings = calculator.calculate(post);
        assertEquals(5, earnings.getQuantityWord());
    }

    @Test
    void givenPostMustCalculateValuePerWord() {
        author.setPremium(false);
        Earnings earnings = calculator.calculate(post);

        assertEquals(author.getPricePerWord(), earnings.getValuePerWord());
    }
}