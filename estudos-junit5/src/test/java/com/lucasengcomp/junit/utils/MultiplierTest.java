package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.EnumOptions;

import static org.junit.jupiter.api.Assertions.*;

class MultiplierTest {

    @ParameterizedTest
    @EnumSource(value = Multiplier.class, names = {"MULTI_DOUBLE", "MULTI_TRIPLE"})
    void applyMultiplier(Multiplier multiplier) {
        Assertions.assertNotNull(multiplier.apllyMultiplier(10.00));
    }

    @ParameterizedTest
    @EnumSource(value = Multiplier.class)
    void applyAllMultiplierEnum(Multiplier multiplier) {
        Assertions.assertNotNull(multiplier.apllyMultiplier(10.00));
    }
}