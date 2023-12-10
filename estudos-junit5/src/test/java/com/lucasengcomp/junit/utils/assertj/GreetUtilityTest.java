package com.lucasengcomp.junit.utils.assertj;

import com.lucasengcomp.junit.CustomDisplayNameGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import static com.lucasengcomp.junit.utils.GreetUtility.greet;
import static com.lucasengcomp.junit.utils.MessagesUtil.*;
import static org.junit.jupiter.api.Assertions.*;

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
}
