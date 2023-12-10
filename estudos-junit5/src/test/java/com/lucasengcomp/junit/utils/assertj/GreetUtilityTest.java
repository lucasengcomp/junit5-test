package com.lucasengcomp.junit.utils.assertj;

import com.lucasengcomp.junit.CustomDisplayNameGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import static com.lucasengcomp.junit.utils.GreetUtility.greet;
import static com.lucasengcomp.junit.utils.MessagesUtil.GOOD_M0RNING;
import static com.lucasengcomp.junit.utils.MessagesUtil.INVALID_HOUR;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class GreetUtilityTest {

    @Test
    void mustCheckMessageGoodMorning() {
        String greetGoodMorning = greet(11);
        assertEquals(GOOD_M0RNING, greetGoodMorning);

        Assertions.assertThat(greetGoodMorning)
                .withFailMessage(INVALID_HOUR)
                .isEqualTo(GOOD_M0RNING);
    }

    @Test
    void shouldThrowException() {
        Assertions.assertThatThrownBy(() -> greet(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_HOUR);
    }
}
